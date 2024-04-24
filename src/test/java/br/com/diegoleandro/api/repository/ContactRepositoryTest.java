package br.com.diegoleandro.api.repository;

import br.com.diegoleandro.api.entity.Contact;
import br.com.diegoleandro.api.entity.Professional;
import br.com.diegoleandro.api.entity.enums.CargoEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ContactRepositoryTest {


    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void testSaveContact() {
        Contact contact = new Contact();
        contact.setNome("Contato Teste");
        contact.setContato("123456");

        Contact savedContact = contactRepository.save(contact);

        assertNotNull(savedContact.getId());
        assertEquals("Contato Teste", savedContact.getNome());
        assertEquals("123456789", savedContact.getContato());
    }


}