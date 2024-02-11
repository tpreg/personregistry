package com.kesmarki.personregistry.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneNumberTest {


	@Test
	public void test_create_phone_number_with_valid_inputs() {
		final var prefix = "123";
		final var number = "4567890";
		final var extension = "1234";
		final var phoneNumber = new PhoneNumber();
		phoneNumber.setPrefix(prefix);
		phoneNumber.setNumber(number);
		phoneNumber.setExtension(extension);
		assertEquals(prefix, phoneNumber.getPrefix());
		assertEquals(number, phoneNumber.getNumber());
		assertEquals(extension, phoneNumber.getExtension());
	}

	@Test
	public void test_phone_number_equality_with_same_inputs() {
		final var phoneNumber1 = new PhoneNumber();
		final UUID id = UUID.randomUUID();
		phoneNumber1.setId(id);
		phoneNumber1.setPrefix("123");
		phoneNumber1.setNumber("4567890");
		phoneNumber1.setExtension("1234");
		final var phoneNumber2 = new PhoneNumber();
		phoneNumber2.setId(id);
		phoneNumber2.setPrefix("123");
		phoneNumber2.setNumber("4567890");
		phoneNumber2.setExtension("1234");
		assertEquals(phoneNumber1, phoneNumber2);
	}

	@Test
	public void test_to_string_method() {
		final var phoneNumber = new PhoneNumber();
		phoneNumber.setPrefix("123");
		phoneNumber.setNumber("4567890");
		phoneNumber.setExtension("1234");
		assertEquals("PhoneNumber{id='null', prefix='123', number='4567890', extension='1234'}", phoneNumber.toString());
	}

	@Test
	public void test_get_id_method() {
		final var id = UUID.randomUUID();
		final var phoneNumber = new PhoneNumber();
		phoneNumber.setId(id);
		assertEquals(id, phoneNumber.getId());
	}

	@Test
	public void test_set_id_method() {
		final var id = UUID.randomUUID();
		final var phoneNumber = new PhoneNumber();
		phoneNumber.setId(id);
		assertEquals(id, phoneNumber.getId());
	}

	@Test
	public void test_create_phone_number_with_null_prefix() {
		assertThrows(IllegalArgumentException.class, () -> {
			final var phoneNumber = new PhoneNumber();
			phoneNumber.setPrefix(null);
			phoneNumber.setNumber("4567890");
			phoneNumber.setExtension("1234");
		});
	}

	@Test
	public void test_create_phone_number_with_null_number() {
		assertThrows(IllegalArgumentException.class, () -> {
			final var phoneNumber = new PhoneNumber();
			phoneNumber.setPrefix("123");
			phoneNumber.setNumber(null);
			phoneNumber.setExtension("1234");
		});
	}

	@Test
	public void test_create_phone_number_with_null_extension() {
		assertThrows(IllegalArgumentException.class, () -> {
			final var phoneNumber = new PhoneNumber();
			phoneNumber.setPrefix("123");
			phoneNumber.setNumber("4567890");
			phoneNumber.setExtension(null);
		});
	}

	@Test
	public void test_phone_number_inequality_with_different_inputs() {
		final var phoneNumber1 = new PhoneNumber();
		phoneNumber1.setPrefix("123");
		phoneNumber1.setNumber("4567890");
		phoneNumber1.setExtension("1234");
		final var phoneNumber2 = new PhoneNumber();
		phoneNumber2.setPrefix("456");
		phoneNumber2.setNumber("7890123");
		phoneNumber2.setExtension("5678");
		assertNotEquals(phoneNumber1, phoneNumber2);
	}

}
