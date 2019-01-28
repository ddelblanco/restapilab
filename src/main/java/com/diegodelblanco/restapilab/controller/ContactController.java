package com.diegodelblanco.restapilab.controller;

import com.diegodelblanco.restapilab.model.Contact;
import com.diegodelblanco.restapilab.repository.ContactRepository;
import com.diegodelblanco.restapilab.resource.ContactResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.Resources;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/hal+json")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    /**
     * Get all contacts.
     *
     * @return a list of contacts
     */
    @GetMapping("/contacts")
    public ResponseEntity<Resources<ContactResource>>  all() {
        final List<ContactResource> collection =
                contactRepository.findAll().stream().map(ContactResource::new).collect(Collectors.toList());
        final Resources<ContactResource> resources = new Resources<>(collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }

    /**
     * Gets a contact by his id.
     *
     * @param id the user id
     * @return the contact if found
     */
    @GetMapping("/contacts/{id}")
    public ResponseEntity<ContactResource> get(@PathVariable final long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            return ResponseEntity.ok(new ContactResource(contact.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create contact.
     *
     * @param contactFromRequest the contact
     * @return the contact
     */
    @PostMapping("/contacts")
    public ResponseEntity<ContactResource> post(@RequestBody final Contact contactFromRequest) {
        final Contact contact = contactRepository.save(new Contact(contactFromRequest));
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                        .path("/{id}")
                        .buildAndExpand(contact.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(new ContactResource(contact));
    }



    /**
     * Update contact.
     *
     * @param id the contact id
     * @param contactFromRequest the information about the contact (a JSON object)
     * @return a Response entity
     */

    @PutMapping("/contacts/{id}")
    public ResponseEntity<ContactResource> put(
            @PathVariable("id") final long id, @RequestBody Contact contactFromRequest) {
        final Contact contact = new Contact(contactFromRequest, id);
        contactRepository.save(contact);
        final ContactResource resource = new ContactResource(contact);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(resource);
    }

    /**
     * Delete contact.
     *
     * @param id the user id
     * @return the contact
     */
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            contactRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
