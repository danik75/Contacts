package com.contacts.tutorial.resources;

import com.codahale.metrics.annotation.Timed;
import com.contacts.tutorial.api.Contact;
import com.contacts.tutorial.dao.ContactDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactsResource {

    private ContactDao contactDao;

    public ContactsResource(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @GET
    @Timed
    @Path("/all")
    public List<Contact> getContacts() {
        return contactDao.getAll();
    }

    @GET
    @Timed
    @Path("/byname")
    public List<Contact> getByName(@QueryParam("firstName") String firstName) {
        return contactDao.getByName(firstName);
    }

    @POST
    @Timed
    @Path("/create")
    public Contact addContact(Contact contact ) {
        int id = contactDao.addContact(contact);
        contact.setId(id);
        return contact;
    }

    @POST
    @Timed
    @Path("/update")
    public Contact updateContact(Contact contact) {
        contactDao.updateContact(contact);
        return contact;
    }

    @DELETE
    @Timed
    @Path("/delete/{id}")
    public void deleteContact(@PathParam("id") int id) {
        contactDao.deleteContact(id);
    }
}
