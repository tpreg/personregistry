package com.kesmarki.personregistry.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AddressTest {


	@Test
	public void test_address_creation_with_required_fields() {
		final var id = UUID.randomUUID();
		final var country = "USA";
		final var zipCode = "12345";
		final var city = "New York";
		final var street = "123 Main St";
		final var address = new Address();
		address.setId(id);
		address.setCountry(country);
		address.setZipCode(zipCode);
		address.setCity(city);
		address.setStreet(street);
		assertEquals(id, address.getId());
		assertEquals(country, address.getCountry());
		assertEquals(zipCode, address.getZipCode());
		assertEquals(city, address.getCity());
		assertEquals(street, address.getStreet());
	}

	@Test
	public void test_address_add_contacts() {
		final var address = new Address();
		final var contact1 = new Contact();
		contact1.setId(UUID.randomUUID());
		final var contact2 = new Contact();
		contact2.setId(UUID.randomUUID());
		final var contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		address.setContacts(contacts);
		assertEquals(2, address.getContacts().size());
		assertTrue(address.getContacts().contains(contact1));
		assertTrue(address.getContacts().contains(contact2));
	}

	@Test
	public void test_address_remove_contacts() {
		final var address = new Address();
		final var contact1 = new Contact();
		contact1.setId(UUID.randomUUID());
		final var contact2 = new Contact();
		contact2.setId(UUID.randomUUID());
		final var contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		address.setContacts(contacts);
		address.getContacts().remove(contact1);
		assertEquals(1, address.getContacts().size());
		assertFalse(address.getContacts().contains(contact1));
		assertTrue(address.getContacts().contains(contact2));
	}

	@Test
	public void test_address_update_fields() {
		final var address = new Address();
		final var id = UUID.randomUUID();
		final var country = "USA";
		final var zipCode = "12345";
		final var city = "New York";
		final var street = "123 Main St";
		address.setContacts(Set.of(new Contact()));
		address.setId(id);
		address.setCountry(country);
		address.setZipCode(zipCode);
		address.setCity(city);
		address.setStreet(street);
		assertEquals(id, address.getId());
		assertEquals(country, address.getCountry());
		assertEquals(zipCode, address.getZipCode());
		assertEquals(city, address.getCity());
		assertEquals(street, address.getStreet());
	}

	@Test
	public void test_address_retrieve_fields() {
		final var id = UUID.randomUUID();
		final var country = "USA";
		final var zipCode = "12345";
		final var city = "New York";
		final var street = "123 Main St";
		final var contacts = Set.of(new Contact());
		final var address = new Address();
		address.setId(id);
		address.setCountry(country);
		address.setZipCode(zipCode);
		address.setCity(city);
		address.setStreet(street);
		address.setContacts(contacts);
		final var retrievedId = address.getId();
		final var retrievedCountry = address.getCountry();
		final var retrievedZipCode = address.getZipCode();
		final var retrievedCity = address.getCity();
		final var retrievedStreet = address.getStreet();
		final var retrievedContacts = address.getContacts();
		assertEquals(id, retrievedId);
		assertEquals(country, retrievedCountry);
		assertEquals(zipCode, retrievedZipCode);
		assertEquals(city, retrievedCity);
		assertEquals(street, retrievedStreet);
		assertEquals(contacts, retrievedContacts);
	}

	@Test
	public void test_address_creation_with_empty_contacts_list() {
		final var address = new Address();
		final var contacts = address.getContacts();
		assertNotNull(contacts);
		assertTrue(contacts.isEmpty());
	}

	@Test
	public void test_address_add_contacts_with_null_values() {
		final var address = new Address();
		final Contact contact1 = null;
		final Contact contact2 = null;
		final var contacts = new HashSet<Contact>();
		contacts.add(null);
		contacts.add(null);
		address.setContacts(contacts);
		assertEquals(1, address.getContacts().size());
		assertTrue(address.getContacts().contains(contact1));
		assertTrue(address.getContacts().contains(contact2));
	}

	@Test
	public void test_address_set_fields_to_null() {
		final var address = new Address();
		address.setContacts(Set.of(new Contact()));
		address.setId(null);
		address.setCountry(null);
		address.setZipCode(null);
		address.setCity(null);
		address.setStreet(null);
		assertNull(address.getId());
		assertNull(address.getCountry());
		assertNull(address.getZipCode());
		assertNull(address.getCity());
		assertNull(address.getStreet());
	}

	@Test
	public void test_address_add_contacts_with_duplicate_values() {
		final var address = new Address();
		final var contact1 = new Contact();
		final var contact2 = new Contact();
		final var contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		contacts.add(contact1);
		address.setContacts(contacts);
		assertEquals(1, address.getContacts().size());
		assertTrue(address.getContacts().contains(contact1));
		assertTrue(address.getContacts().contains(contact2));
	}

	@Test
	public void test_address_add_contacts_with_invalid_values() {
		final var address = new Address();
		final var contact1 = new EmailAddress();
		assertThrows(IllegalArgumentException.class, () -> contact1.setEmail("invalid_email"));
	}

	@Test
	public void test_address_add_contacts_with_non_unique_ids() {
		final var address = new Address();
		final var contact1 = new Contact();
		final var contact2 = new Contact();
		contact1.setId(UUID.randomUUID());
		contact2.setId(UUID.randomUUID());
		address.setContacts(Set.of(contact1, contact2));
		assertEquals(2, address.getContacts().size());
		assertTrue(address.getContacts().contains(contact1));
		assertTrue(address.getContacts().contains(contact2));
	}

	@Test
	public void test_address_add_contacts_with_non_unique_email_addresses() {
		final var address = new Address();
		final var contact1 = new EmailAddress();
		contact1.setId(UUID.randomUUID());
		final var contact2 = new EmailAddress();
		contact2.setId(UUID.randomUUID());
		contact1.setEmail("test@example.com");
		contact2.setEmail("test@example.com");
		address.setContacts(Set.of(contact1, contact2));
		assertEquals(2, address.getContacts().size());
		assertTrue(address.getContacts().contains(contact1));
		assertTrue(address.getContacts().contains(contact2));
	}

}
