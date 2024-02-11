package com.kesmarki.personregistry.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailAddressTest {


	@Test
	public void test_create_email_address_with_valid_email() {
		final var validEmail = "test@example.com";
		final var emailAddress = new EmailAddress(validEmail);
		assertEquals(validEmail, emailAddress.getEmail());
	}

	@Test
	public void test_email_address_equality() {
		final var validEmail = "test@example.com";
		final var emailAddress1 = new EmailAddress(validEmail);
		final var emailAddress2 = new EmailAddress(validEmail);
		assertEquals(emailAddress1, emailAddress2);
	}

	@Test
	public void test_to_string_method() {
		final var validEmail = "test@example.com";
		final var emailAddress = new EmailAddress(validEmail);
		assertEquals("EmailAddress{email='%s'}".formatted(validEmail), emailAddress.toString());
	}

	@Test
	public void test_set_email_attribute_with_invalid_email_exception() {
		assertThrows(IllegalArgumentException.class, () -> new EmailAddress("invalid_email"));
	}

	@Test
	public void test_set_email_attribute_with_null_email_exception() {
		assertThrows(NullPointerException.class, () -> new EmailAddress(null));
	}

	@Test
	public void test_set_email_attribute_with_long_email_exception() {
		final var longEmail = "a".repeat(65);
		assertThrows(IllegalArgumentException.class, () -> new EmailAddress(longEmail));
	}

}
