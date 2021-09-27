package com.contacts.tutorial;

import com.contacts.tutorial.dao.ContactDao;
import com.contacts.tutorial.exception.AppExceptionMapper;
import com.contacts.tutorial.resources.ContactsResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class ContactsApplication extends Application<ContactsConfiguration> {

    public static void main(String[] args) throws Exception {
        new ContactsApplication().run(args);
    }

    @Override
    public void run(ContactsConfiguration contactsConfiguration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, contactsConfiguration.getDataSourceFactory(),"h2");
        final ContactDao contactDao = jdbi.onDemand(ContactDao.class);
        contactDao.createContactTable();
        environment.jersey().register(new ContactsResource(contactDao));
        environment.jersey().register(new AppExceptionMapper());
    }
}
