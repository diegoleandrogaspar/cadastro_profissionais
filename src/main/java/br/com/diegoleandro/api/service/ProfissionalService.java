package br.com.diegoleandro.api.service;

import br.com.diegoleandro.api.entity.Profissional;
import br.com.diegoleandro.api.exception.ProfissionalNaoEncontradoException;
import br.com.diegoleandro.api.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfissionalService {

    public static final String MSG_PROFISSIONAL_NAO_ENCONTRADO =
            "Não existe um cadastro de profissional com o código %d";

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Transactional
    public Profissional salvar(Profissional profissional){
        return profissionalRepository.save(profissional);
    }

    @Transactional
    public void excluir(Long profissionalId) {
        try {
            profissionalRepository.deleteById(profissionalId);
        } catch (EmptyResultDataAccessException e){

            throw new ProfissionalNaoEncontradoException(
                    String.format(MSG_PROFISSIONAL_NAO_ENCONTRADO, profissionalId)
            );
        }
    }

    public Profissional buscarOuFalhar(Long profissionalId) {
        return profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new ProfissionalNaoEncontradoException(
                String.format(MSG_PROFISSIONAL_NAO_ENCONTRADO, profissionalId)
        ));
    }


}
