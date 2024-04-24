package br.com.diegoleandro.api.assembler;

import br.com.diegoleandro.api.controller.request.ContactRequestDTO;
import br.com.diegoleandro.api.controller.response.ContactResponseDTO;
import br.com.diegoleandro.api.controller.response.ProfessionalResponseDTO;
import br.com.diegoleandro.api.entity.Contact;
import br.com.diegoleandro.api.entity.Professional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactConverter  implements Converter<Contact, ContactResponseDTO, ContactRequestDTO>{

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Contact toDomainObject(ContactRequestDTO contactRequestDTO) {
        return modelMapper.map(contactRequestDTO, Contact.class);
    }

    @Override
    public ContactResponseDTO toDto(Contact contact) {

        ContactResponseDTO responseDTO = modelMapper.map(contact, ContactResponseDTO.class);
        responseDTO.setProfissionalId(modelMapper.map(contact.getProfessionalId(), ProfessionalResponseDTO.class));
        return responseDTO;

    }

    @Override
    public List<ContactResponseDTO> toCollectionDTO(List<Contact> contactList) {
        return contactList.stream()
                .map(contact -> toDto(contact))
                .collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(ContactRequestDTO contactRequestDTO, Contact contact) {
        //Para evitar org.hibernate.HibernateException: identifier of an instance of
        // br.com.diegoleandro.api.entity.Contact was altered from 8 to 9
        contact.setProfessionalId(new Professional());

        modelMapper.map(contactRequestDTO, contact);
    }
}
