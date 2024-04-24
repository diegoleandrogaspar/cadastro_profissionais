package br.com.diegoleandro.api.controller;

import br.com.diegoleandro.api.assembler.ContactConverter;
import br.com.diegoleandro.api.controller.request.ContactRequestDTO;
import br.com.diegoleandro.api.controller.response.ContactResponseDTO;
import br.com.diegoleandro.api.entity.Contact;
import br.com.diegoleandro.api.exception.BusinessException;
import br.com.diegoleandro.api.exception.ResourceNotFoundException;
import br.com.diegoleandro.api.service.ContactService;
import br.com.diegoleandro.api.repository.specifications.ContactSpecifications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContactController {

    @Autowired
    ContactConverter contactConverter;

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @GetMapping
    public List<ContactResponseDTO> getAll(ContactSpecifications contactSpecifications) {
       return contactConverter.toCollectionDTO(contactService.getAllProfessional(contactSpecifications));
    }

    @GetMapping("/{contactId}")
    public ContactResponseDTO getId(@PathVariable Long contactId) {
        Contact contact = contactService.findByIdOrThrow(contactId);

        return contactConverter.toDto(contact);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponseDTO create(@RequestBody @Valid ContactRequestDTO contactRequestDTO) {
        try {
            Contact contact = contactConverter.toDomainObject(contactRequestDTO);

            return contactConverter.toDto(contactService.createContact(contact));
        } catch (ResourceNotFoundException ex){
            throw new BusinessException(ex.getMessage());
        }
    }

    @PutMapping("/{contactId}")
    public ContactResponseDTO update(@PathVariable Long contactId, @RequestBody @Valid ContactRequestDTO contactRequestDTO) {
        try {
            Contact contactActual = contactService.findByIdOrThrow(contactId);

            Contact updatedContact = contactConverter.toDomainObject(contactRequestDTO);

            BeanUtils.copyProperties(updatedContact, contactActual, "id", "createdDate");

            Contact savedContact = contactService.createContact(contactActual);

            return contactConverter.toDto(savedContact);
        } catch (ResourceNotFoundException ex) {
            throw new BusinessException("Contato não encontrado com o ID: " + contactId);
        }
    }

    @DeleteMapping("/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long contactId) {
        contactService.findByIdOrThrow(contactId);
    }

}
