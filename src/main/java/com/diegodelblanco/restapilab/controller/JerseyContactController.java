package com.diegodelblanco.restapilab.controller;

import com.diegodelblanco.restapilab.model.Contact;
import com.diegodelblanco.restapilab.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/api/v2/contacts")
public class JerseyContactController {

    @Autowired
    private ContactRepository contactRepository;
    
    @GET
    @Produces("application/json")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    public Response createContact(Contact contact) throws URISyntaxException
    {
        if(contact.getName() == null || contact.getSurname() == null) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        final Contact newContact = contactRepository.save(contact);
        return Response.status(201).contentLocation(new URI("http://localhost:8080/api/v1/contacts/"+newContact.getId())).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getContactById(@PathParam("id") long id) throws URISyntaxException
    {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            return Response
                    .status(200)
                    .entity(contact)
                    .contentLocation(new URI("http://localhost:8080/api/v1/contacts/"+id)).build();
        } else {
            return Response.status(404).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateContact(@PathParam("id") long id, Contact contactData)
    {

        Optional<Contact> contact =
                contactRepository
                        .findById(id);
        if(contact.isPresent()) {
            Contact contactTemp = contact.get();
            contactTemp.setName(contactData.getName());
            contactTemp.setSurname(contactData.getSurname());
            contactTemp.setEmail(contactData.getEmail());
            contactTemp.setPhone(contactData.getPhone());
            contactTemp.setBirthDate(contactData.getBirthDate());

            try {
                final Contact newContact = contactRepository.save(contactTemp);
                return Response.status(200).entity(newContact).build();
            } catch (Exception ex) {
                return Response.status(400).entity("Please provide correct inputs").build();
            }
        } else {
            return Response.status(404).build();
        }


    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") long id) {

        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
            return Response.status(200).build();
        } else {
            return Response.status(404).build();
        }
    }
}
