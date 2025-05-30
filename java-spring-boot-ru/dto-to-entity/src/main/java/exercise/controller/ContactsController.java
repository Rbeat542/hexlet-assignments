package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO postContact(@RequestBody ContactCreateDTO contactData) {
        var contact = toEntity(contactData);
        contactRepository.save(contact);
        var contactDTO = toDTO(contact);
        return contactDTO;
    }

    public Contact toEntity(ContactCreateDTO data) {
        var contact = new Contact();
        //contact.setId(data.getId()); // не нужно, так как айди еще нет
        contact.setFirstName(data.getFirstName());
        contact.setLastName(data.getLastName());
        contact.setPhone(data.getPhone());
        return contact;
    }

    public ContactDTO toDTO(Contact data) {
        var contact = new ContactDTO();
        contact.setId(data.getId());  // а здесь нужно, так как полное копирование сущности
        contact.setFirstName(data.getFirstName());
        contact.setLastName(data.getLastName());
        contact.setPhone(data.getPhone());
        contact.setCreatedAt(data.getCreatedAt());
        contact.setUpdatedAt(data.getUpdatedAt());
        return contact;
    }

    // END
}
