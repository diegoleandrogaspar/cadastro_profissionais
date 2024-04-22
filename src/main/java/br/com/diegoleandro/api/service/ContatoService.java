package br.com.diegoleandro.api.service;

import br.com.diegoleandro.api.entity.Contato;
import br.com.diegoleandro.api.entity.Profissional;
import br.com.diegoleandro.api.exception.ContatoNaoEncontradoException;
import br.com.diegoleandro.api.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContatoService {

    public static final String MSG_CONTATO_NAO_ENCONTRADO = "Não existe cadastro de contato com o código %d";
    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ProfissionalService profissionalService;


    @Transactional
    public Contato salvar(Contato contato){
        Long profissionalId = contato.getProfissional().getId();

        Profissional profissional = profissionalService.buscarOuFalhar(profissionalId);

        contato.setProfissional(profissional);

        return contatoRepository.save(contato);

    }

    @Transactional
    public void excluir(Long contatoId) {
        try {
            contatoRepository.deleteById(contatoId);
        } catch (EmptyResultDataAccessException e){
            throw new ContatoNaoEncontradoException(
              String.format( MSG_CONTATO_NAO_ENCONTRADO, contatoId));
        }
    }


    public Contato buscarOuFalhar(Long contatoId){
        return contatoRepository.findById(contatoId)
                .orElseThrow(() -> new ContatoNaoEncontradoException(
                String.format(MSG_CONTATO_NAO_ENCONTRADO, contatoId)));
    }
}
