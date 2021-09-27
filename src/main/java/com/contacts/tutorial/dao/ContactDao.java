package com.contacts.tutorial.dao;

import com.contacts.tutorial.api.Contact;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface ContactDao {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS Contact (id INT NOT NULL AUTO_INCREMENT, firstName VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL)")
    void createContactTable();

    @SqlUpdate("INSERT INTO Contact (firstName,lastName,email) VALUES(:firstName, :lastName, :email)")
    @GetGeneratedKeys
    int addContact(@BindBean Contact contact);

    @SqlUpdate("UPDATE Contact SET firstName=:firstName, lastName=:lastName, email=:email WHERE id=:id")
    int updateContact(@BindBean Contact contact);

    @SqlQuery("SELECT * FROM Contact")
    List<Contact> getAll();

    @SqlQuery("SELECT * FROM Contact WHERE firstName=:firstName")
    List<Contact> getByName(@Bind("firstName") String firstName);

    @SqlUpdate("DELETE FROM Contact WHERE id = :id")
    void deleteContact(@Bind("id") int id);
}
