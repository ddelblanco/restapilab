package com.diegodelblanco.restapilab.controller;

import com.diegodelblanco.restapilab.model.Contact;
import com.diegodelblanco.restapilab.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    /**
     * Get all contacts.
     *
     * @return a list of contacts
     */
    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok().body(contactRepository.findAll());
    }

    /**
     * Gets a contact by his id.
     *
     * @param contactId the user id
     * @return the contact if found
     */
    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getUsersById(@PathVariable(value = "id") Long contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        if (contact.isPresent()) {
            return ResponseEntity.ok().body(contact.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create contact.
     *
     * @param contact the contact
     * @return the contact
     */
    @PostMapping("/contacts")
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
        try {
            final Contact newContact = contactRepository.save(contact);
            URI uri = new URI("http://localhost:8080/api/v1/contacts/" + newContact.getId());
            return ResponseEntity.created(uri).body(newContact);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }


    /**
     * Update contact.
     *
     * @param contactId the contact id
     * @param contactData the information about the contact (a JSON object)
     * @return a Response entity
     */

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(
            @PathVariable(value = "id") Long contactId, @Valid @RequestBody  Contact contactData) {
        Optional<Contact> contact =
                contactRepository
                        .findById(contactId);
        if(contact.isPresent()) {
            Contact contactTemp = contact.get();
            contactTemp.setName(contactData.getName());
            contactTemp.setSurname(contactData.getSurname());
            contactTemp.setEmail(contactData.getEmail());
            contactTemp.setPhone(contactData.getPhone());
            contactTemp.setBirthDate(contactData.getBirthDate());

            try {
                final Contact newContact = contactRepository.save(contactTemp);
                URI uri = new URI("http://localhost:8080/api/v1/contacts/" + newContact.getId());
                return ResponseEntity.created(uri).body(newContact);
            } catch (Exception ex) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete contact.
     *
     * @param contactId the user id
     * @return the contact
     * @throws Exception the exception
     */
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Contact> deleteContact(@PathVariable(value = "id") Long contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
            return ResponseEntity.ok().body(contact.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
