package com.diegodelblanco.restapilab.resource;

import com.diegodelblanco.restapilab.controller.ContactController;
import com.diegodelblanco.restapilab.model.Contact;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ContactResource extends ResourceSupport {
    private final Contact contact;
    public ContactResource(final Contact contact) {
        this.contact = contact;
        final long id = contact.getId();
        add(linkTo(ContactController.class).withRel("contact"));
        add(linkTo(methodOn(ContactController.class).get(id)).withSelfRel());
    }

    public Contact getContact() {
        return contact;
    }
}
