package com.kesmarki.personregistry.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneNumberTest {


	@Test
	public void test_create_phone_number_with_valid_inputs() {
		final var id = UUID.randomUUID();
		final var prefix = "123";
		final var number = "4567890";
		final var extension = "1234";
		final var phoneNumber = new PhoneNumber(id, prefix, number, extension);
		assertEquals(id, phoneNumber.getId());
		assertEquals(prefix, phoneNumber.getPrefix());
		assertEquals(number, phoneNumber.getNumber());
		assertEquals(extension, phoneNumber.getExtension());
	}

	@Test
	public void test_phone_number_equality_with_same_inputs() {
		final UUID id = UUID.randomUUID();
		final var prefix = "123";
		final var number = "4567890";
		final var extension = "1234";
		final var phoneNumber1 = new PhoneNumber(id, prefix, number, extension);
		final var phoneNumber2 = new PhoneNumber(id, prefix, number, extension);
		assertEquals(phoneNumber1, phoneNumber2);
	}

	@Test
	public void test_to_string_method() {
		final var phoneNumber = new PhoneNumber(UUID.fromString("ec7ba920-7952-411e-a431-bd16cb052f96"), "123", "4567890", "1234");
		assertEquals("PhoneNumber{id='ec7ba920-7952-411e-a431-bd16cb052f96', prefix='123', number='4567890', extension='1234'}", phoneNumber.toString());
	}

	@Test
	public void test_create_phone_number_with_null_prefix() {
		assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(UUID.randomUUID(), null, "4567890", "1234"));
	}

	@Test
	public void test_create_phone_number_with_null_number() {
		assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(UUID.randomUUID(), "123", null, "1234"));
	}

	@Test
	public void test_create_phone_number_with_null_extension() {
		assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(UUID.randomUUID(), "123", "4567890", null));
	}

	@Test
	public void test_phone_number_inequality_with_different_inputs() {
		final var phoneNumber1 = new PhoneNumber(UUID.randomUUID(), "123", "4567890", "1234");
		final var phoneNumber2 = new PhoneNumber(UUID.randomUUID(), "456", "7890123", "5678");
		assertNotEquals(phoneNumber1, phoneNumber2);
	}

}
