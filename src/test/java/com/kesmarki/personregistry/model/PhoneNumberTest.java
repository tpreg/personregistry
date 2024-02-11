package com.kesmarki.personregistry.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneNumberTest {


	@Test
	public void test_create_phone_number_with_valid_inputs() {
		final var prefix = "123";
		final var number = "4567890";
		final var extension = "1234";
		final var phoneNumber = new PhoneNumber(prefix, number, extension);
		assertEquals(prefix, phoneNumber.getPrefix());
		assertEquals(number, phoneNumber.getNumber());
		assertEquals(extension, phoneNumber.getExtension());
	}

	@Test
	public void test_phone_number_equality_with_same_inputs() {
		final var prefix = "123";
		final var number = "4567890";
		final var extension = "1234";
		final var phoneNumber1 = new PhoneNumber(prefix, number, extension);
		final var phoneNumber2 = new PhoneNumber(prefix, number, extension);
		assertEquals(phoneNumber1, phoneNumber2);
	}

	@Test
	public void test_to_string_method() {
		final var phoneNumber = new PhoneNumber("123", "4567890", "1234");
		assertEquals("PhoneNumber{prefix='123', number='4567890', extension='1234'}", phoneNumber.toString());
	}

	@Test
	public void test_create_phone_number_with_null_prefix() {
		assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(null, "4567890", "1234"));
	}

	@Test
	public void test_create_phone_number_with_null_number() {
		assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("123", null, "1234"));
	}

	@Test
	public void test_create_phone_number_with_null_extension() {
		assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("123", "4567890", null));
	}

	@Test
	public void test_phone_number_inequality_with_different_inputs() {
		final var phoneNumber1 = new PhoneNumber("123", "4567890", "1234");
		final var phoneNumber2 = new PhoneNumber("456", "7890123", "5678");
		assertNotEquals(phoneNumber1, phoneNumber2);
	}

}
