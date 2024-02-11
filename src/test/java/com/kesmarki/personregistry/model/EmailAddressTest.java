package com.kesmarki.personregistry.model;


import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailAddressTest {


	@Test
	public void test_create_email_address_with_valid_email() {
		final var validEmail = "test@example.com";
		final var emailAddress = new EmailAddress();
		emailAddress.setEmail(validEmail);
		assertEquals(validEmail, emailAddress.getEmail());
	}

	@Test
	public void test_set_email_attribute_with_valid_email() {
		final var validEmail = "test@example.com";
		final var emailAddress = new EmailAddress();
		emailAddress.setEmail(validEmail);
		assertEquals(validEmail, emailAddress.getEmail());
	}

	@Test
	public void test_get_email_attribute() {
		final var validEmail = "test@example.com";
		final var emailAddress = new EmailAddress();
		emailAddress.setEmail(validEmail);
		assertEquals(validEmail, emailAddress.getEmail());
	}

	@Test
	public void test_email_address_equality() {
		final var validEmail = "test@example.com";
		final var emailAddress1 = new EmailAddress();
		emailAddress1.setEmail(validEmail);
		final var emailAddress2 = new EmailAddress();
		emailAddress2.setEmail(validEmail);
		assertEquals(emailAddress1, emailAddress2);
	}

	@Test
	public void test_to_string_method() {
		final var id = UUID.fromString("d125c8eb-86fa-41d2-9762-9372cd0b2814");
		final var validEmail = "test@example.com";
		final var emailAddress = new EmailAddress();
		emailAddress.setId(id);
		emailAddress.setEmail(validEmail);
		assertEquals("EmailAddress{id='%s', email='%s'}".formatted(id, validEmail), emailAddress.toString());
	}

	@Test
	public void test_set_email_attribute_with_invalid_email_exception() {
		final var invalidEmail = "invalid_email";
		final var emailAddress = new EmailAddress();
		assertThrows(IllegalArgumentException.class, () -> emailAddress.setEmail(invalidEmail));
	}

	@Test
	public void test_set_email_attribute_with_null_email_exception() {
		final var emailAddress = new EmailAddress();
		assertThrows(NullPointerException.class, () -> emailAddress.setEmail(null));
	}

	@Test
	public void test_set_email_attribute_with_long_email_exception() {
		final var longEmail = "a".repeat(65);
		final var emailAddress = new EmailAddress();
		assertThrows(IllegalArgumentException.class, () -> emailAddress.setEmail(longEmail));
	}

}
