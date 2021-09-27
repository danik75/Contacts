package com.contacts.tutorial.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;

class ContactTest {

    private static Contact contact = new Contact();

    private static ObjectMapper MAPPER = Jackson.newObjectMapper();

    @BeforeAll
    public static void init(){
        contact.setEmail("assaf@email.com");
        contact.setFirstName("Assaf");
        contact.setLastName("Pritsker");
        contact.setId(2);
    }

    @Test
    public void serializesToJSON() throws Exception {
        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/contact.json"), Contact.class));
        assert(MAPPER.writeValueAsString(contact)).equals(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        Contact contactDeserialized = MAPPER.readValue(fixture("fixtures/contact.json"), Contact.class);
        assert(contactDeserialized.toString()).equals(contact.toString());
    }

}