package com.kesmarki.personregistry.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MobileNumberTest {


	@Test
	public void test_create_mobile_number_with_valid_prefix_and_number() {
		final var prefix = "123";
		final var number = "4567890";
		final var mobileNumber = new MobileNumber();
		mobileNumber.setPrefix(prefix);
		mobileNumber.setNumber(number);
		assertEquals(prefix, mobileNumber.getPrefix());
		assertEquals(number, mobileNumber.getNumber());
	}

	@Test
	public void test_mobile_numbers_with_same_prefix_and_number_are_equal() {
		final var prefix = "123";
		final var number = "4567890";
		final var mobileNumber1 = new MobileNumber();
		mobileNumber1.setPrefix(prefix);
		mobileNumber1.setNumber(number);
		final var mobileNumber2 = new MobileNumber();
		mobileNumber2.setPrefix(prefix);
		mobileNumber2.setNumber(number);
		assertEquals(mobileNumber1, mobileNumber2);
	}

	@Test
	public void test_mobile_number_string_representation_includes_prefix_and_number() {
		final var prefix = "123";
		final var number = "4567890";
		final var mobileNumber = new MobileNumber();
		mobileNumber.setPrefix(prefix);
		mobileNumber.setNumber(number);
		assertEquals("MobileNumber{id='%s', prefix='%s', number='%s'}".formatted(mobileNumber.getId(), prefix, number), mobileNumber.toString());
	}

	@Test
	public void test_mobile_number_inherits_id_field_from_contact_superclass() {
		final var id = UUID.randomUUID();
		final var contact = new Contact();
		contact.setId(id);
		final var mobileNumber = new MobileNumber();
		mobileNumber.setId(id);
		assertEquals(id, mobileNumber.getId());
	}

	@Test
	public void test_create_mobile_number_with_null_prefix_throws_null_pointer_exception() {
		final var number = "4567890";
		assertThrows(IllegalArgumentException.class, () -> {
			final var mobileNumber = new MobileNumber();
			mobileNumber.setPrefix(null);
			mobileNumber.setNumber(number);
		});
	}

	@Test
	public void test_create_mobile_number_with_null_number_throws_null_pointer_exception() {
		final var prefix = "123";
		assertThrows(IllegalArgumentException.class, () -> {
			final var mobileNumber = new MobileNumber();
			mobileNumber.setPrefix(prefix);
			mobileNumber.setNumber(null);
		});
	}

	@Test
	public void test_mobile_number_not_equal_to_objects_of_other_classes() {
		final var mobileNumber = new MobileNumber();
		final var otherObject = new Object();
		assertNotEquals(mobileNumber, otherObject);
	}

	@Test
	public void test_mobile_number_not_equal_to_null() {
		final var mobileNumber = new MobileNumber();
		assertNotEquals(mobileNumber, null);
	}

}
