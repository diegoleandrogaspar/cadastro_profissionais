package br.com.diegoleandro.api.domain.service;

import br.com.diegoleandro.api.domain.entity.Contact;
import br.com.diegoleandro.api.domain.entity.Professional;
import br.com.diegoleandro.api.web.exception.ResourceNotFoundException;
import br.com.diegoleandro.infrastructure.repository.ContactRepository;
import br.com.diegoleandro.infrastructure.repository.specifications.ContactSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactService {

    public static final String MSG_CONTATO_NAO_ENCONTRADO = "Não existe cadastro de contato com o código %d";

    @Autowired
    ProfessionalService professionalService;

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllProfessional(ContactSpecifications contactSpec) {
        return contactRepository.findAll(contactSpec);
    }

    @Transactional
    public Contact createContact(Contact contact){
        Long profissionalId = contact.getProfessionalId().getId();

        Professional professional = professionalService.findByIdOrThrow(profissionalId);

        contact.setProfessionalId(professional);

        return contactRepository.save(contact);

    }

    @Transactional
    public void delete(Long contatoId) {
        try {
            contactRepository.deleteById(contatoId);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(
              String.format( MSG_CONTATO_NAO_ENCONTRADO, contatoId));
        }
    }

    public Contact findByIdOrThrow(Long contatoId){
        return contactRepository.findById(contatoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                String.format(MSG_CONTATO_NAO_ENCONTRADO, contatoId)));
    }
}
