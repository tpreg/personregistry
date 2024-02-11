package com.kesmarki.personregistry.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
		final var address = new Address(id, country, zipCode, city, street, Set.of());
		assertEquals(id, address.getId());
		assertEquals(country, address.getCountry());
		assertEquals(zipCode, address.getZipCode());
		assertEquals(city, address.getCity());
		assertEquals(street, address.getStreet());
	}

	@Test
	public void test_address_add_contacts() {
		final var contact1 = new Contact(UUID.randomUUID());
		final var contact2 = new Contact(UUID.randomUUID());
		final var contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		final var address = new Address(UUID.randomUUID(), "", "", "", "", contacts);
		assertEquals(2, address.getContacts().size());
		assertTrue(address.getContacts().contains(contact1));
		assertTrue(address.getContacts().contains(contact2));
	}

	@Test
	public void test_address_remove_contacts() {
		final var contact1 = new Contact(UUID.randomUUID());
		final var contact2 = new Contact(UUID.randomUUID());
		final var contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		final var address = new Address(UUID.randomUUID(), "", "", "", "", contacts);
		address.getContacts().remove(contact1);
		assertEquals(1, address.getContacts().size());
		assertFalse(address.getContacts().contains(contact1));
		assertTrue(address.getContacts().contains(contact2));
	}

	@Test
	public void test_address_update_fields() {
		final var id = UUID.randomUUID();
		final var country = "USA";
		final var zipCode = "12345";
		final var city = "New York";
		final var street = "123 Main St";
		final var contacts = Set.of(new Contact());
		final var address = new Address(id, country, zipCode, city, street, contacts);
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
		final var address = new Address(id, country, zipCode, city, street, contacts);
		assertEquals(id, address.getId());
		assertEquals(country, address.getCountry());
		assertEquals(zipCode, address.getZipCode());
		assertEquals(city, address.getCity());
		assertEquals(street, address.getStreet());
		assertEquals(contacts, address.getContacts());
	}

	@Test
	public void test_address_creation_with_empty_contacts_list() {
		final var id = UUID.randomUUID();
		final var country = "USA";
		final var zipCode = "12345";
		final var city = "New York";
		final var street = "123 Main St";
		final var address = new Address(id, country, zipCode, city, street, Set.of());
		final var contacts = address.getContacts();
		assertNotNull(contacts);
		assertTrue(contacts.isEmpty());
	}

	@Test
	public void test_address_add_contacts_with_null_values() {
		final Contact contact1 = null;
		final Contact contact2 = null;
		final var contacts = new HashSet<Contact>();
		contacts.add(null);
		contacts.add(null);
		final var address = new Address(UUID.randomUUID(), "", "", "", "", contacts);
		assertEquals(1, address.getContacts().size());
		assertTrue(address.getContacts().contains(contact1));
		assertTrue(address.getContacts().contains(contact2));
	}

	@Test
	public void test_address_set_fields_to_null() {
		assertThrows(IllegalArgumentException.class, () -> new Address(null, null, null, null, null, Set.of(new Contact())));
	}

	@Test
	public void test_address_add_contacts_with_duplicate_values() {
		final var contact1 = new Contact();
		final var contact2 = new Contact();
		final var contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		contacts.add(contact1);
		final var address = new Address(UUID.randomUUID(), "", "", "", "", contacts);
		assertEquals(1, address.getContacts().size());
		assertTrue(address.getContacts().contains(contact1));
		assertTrue(address.getContacts().contains(contact2));
	}

	@Test
	public void test_address_add_contacts_with_non_unique_ids() {
		final var contact1 = new Contact(UUID.randomUUID());
		final var contact2 = new Contact(UUID.randomUUID());
		final var address = new Address(UUID.randomUUID(), "", "", "", "", Set.of(contact1, contact2));
		assertEquals(2, address.getContacts().size());
		assertTrue(address.getContacts().contains(contact1));
		assertTrue(address.getContacts().contains(contact2));
	}

	@Test
	public void test_address_add_contacts_with_non_unique_email_addresses() {
		final var contact1 = new EmailAddress(UUID.randomUUID(), "test@example.com");
		final var contact2 = new EmailAddress(UUID.randomUUID(), "test@example.com");
		final var address = new Address(UUID.randomUUID(), "", "", "", "", Set.of(contact1, contact2));
		assertEquals(2, address.getContacts().size());
		assertTrue(address.getContacts().contains(contact1));
		assertTrue(address.getContacts().contains(contact2));
	}

}
