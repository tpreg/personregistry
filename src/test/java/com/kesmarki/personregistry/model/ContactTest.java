package com.kesmarki.personregistry.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactTest {


	@Test
	public void test_id_field_is_null() {
		final var contact = new Contact();
		assertNull(contact.getId());
	}

	@Test
	public void test_set_uuid_value_to_id_field() {
		final var id = UUID.randomUUID();
		final var contact = new Contact();
		contact.setId(id);
		assertEquals(id, contact.getId());
	}

	@Test
	public void test_set_id_field_to_null() {
		final var id = UUID.randomUUID();
		final var contact = new Contact();
		contact.setId(id);
		assertThrows(IllegalArgumentException.class, () -> contact.setId(null));
	}

	@Test
	public void test_to_string_method() {
		final var contact = new Contact();
		contact.setId(UUID.fromString("62f05cb5-9093-490b-a1ff-777ca5acaa84"));
		final var expected = "Contact{id='62f05cb5-9093-490b-a1ff-777ca5acaa84'}";
		assertEquals(expected, contact.toString());
	}

	@Test
	public void test_equals_method_identical_object() {
		final var contact1 = new Contact();
		final var contact2 = new Contact();
		assertEquals(contact1, contact2);
	}

	@Test
	public void test_equals_method_different_object() {
		final var contact = new Contact();
		final var obj = new Object();
		assertNotEquals(contact, obj);
	}

}
