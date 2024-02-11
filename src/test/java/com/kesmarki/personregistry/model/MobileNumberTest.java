package com.kesmarki.personregistry.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MobileNumberTest {


	private static MobileNumber createMobileNumber() {
		return new MobileNumber("123", "4567890");
	}

	@Test
	public void test_create_mobile_number_with_valid_prefix_and_number() {
		final var prefix = "123";
		final var number = "4567890";
		final var mobileNumber = new MobileNumber(prefix, number);
		assertEquals(prefix, mobileNumber.getPrefix());
		assertEquals(number, mobileNumber.getNumber());
	}

	@Test
	public void test_mobile_numbers_with_same_prefix_and_number_are_equal() {
		final var prefix = "123";
		final var number = "4567890";
		final var mobileNumber1 = new MobileNumber(prefix, number);
		final var mobileNumber2 = new MobileNumber(prefix, number);
		assertEquals(mobileNumber1, mobileNumber2);
	}

	@Test
	public void test_mobile_number_string_representation_includes_prefix_and_number() {
		final var prefix = "123";
		final var number = "4567890";
		final var mobileNumber = new MobileNumber(prefix, number);
		assertEquals("MobileNumber{prefix='%s', number='%s'}".formatted(prefix, number), mobileNumber.toString());
	}

	@Test
	public void test_mobile_number_inherits_id_field_from_contact_superclass() {
		final var contact = new Contact();
		final var mobileNumber = new MobileNumber("123", "4567890");
		assertEquals(contact.getId(), mobileNumber.getId());
	}

	@Test
	public void test_create_mobile_number_with_null_prefix_throws_null_pointer_exception() {
		assertThrows(IllegalArgumentException.class, () -> new MobileNumber(null, "4567890"));
	}

	@Test
	public void test_create_mobile_number_with_null_number_throws_null_pointer_exception() {
		assertThrows(IllegalArgumentException.class, () -> new MobileNumber("123", null));
	}

	@Test
	public void test_mobile_number_not_equal_to_objects_of_other_classes() {
		final var mobileNumber = createMobileNumber();
		final var otherObject = new Object();
		assertNotEquals(mobileNumber, otherObject);
	}

	@Test
	public void test_mobile_number_not_equal_to_null() {
		final var mobileNumber = createMobileNumber();
		assertNotEquals(mobileNumber, null);
	}

}
