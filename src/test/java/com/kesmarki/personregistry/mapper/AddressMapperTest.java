package com.kesmarki.personregistry.mapper;

import com.kesmarki.personregistry.dto.AddressDto;
import com.kesmarki.personregistry.dto.ContactDto;
import com.kesmarki.personregistry.model.Address;
import com.kesmarki.personregistry.model.Contact;
import com.kesmarki.personregistry.model.ContactType;
import com.kesmarki.personregistry.model.EmailAddress;
import com.kesmarki.personregistry.model.MobileNumber;
import com.kesmarki.personregistry.model.PhoneNumber;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AddressMapperTest {

	@Autowired
	private AddressMapper addressMapper;

	@Test
	public void test_map_entity_to_dto() {
		final var emailAddress = new EmailAddress(UUID.randomUUID(), "email@example.com");
		final var mobileNumber = new MobileNumber(UUID.randomUUID(), "123", "4567890");
		final var phoneNumber = new PhoneNumber(UUID.randomUUID(), "123", "4567890", "1234");
		final var address = new Address(UUID.randomUUID(), "Country", "12345", "City", "Street", Set.of(emailAddress, mobileNumber, phoneNumber));
		final var addressDto = this.addressMapper.toDto(address);
		assertNotNull(addressDto);
		assertEquals(address.getId(), addressDto.id());
		assertEquals(address.getCountry(), addressDto.country());
		assertEquals(address.getZipCode(), addressDto.zipCode());
		assertEquals(address.getCity(), addressDto.city());
		assertEquals(address.getStreet(), addressDto.street());
		assertEquals(3, addressDto.contacts().size());
	}

	@Test
	public void test_map_dto_to_entity() {
		final var addressDto = new AddressDto(UUID.randomUUID(), "Country", "12345", "City", "Street", Set.of(
				new ContactDto(ContactType.EMAIL, UUID.randomUUID(), "email@example.com", null, null, null),
				new ContactDto(ContactType.MOBILE, UUID.randomUUID(), null, "123", "4567890", null),
				new ContactDto(ContactType.LANDLINE, UUID.randomUUID(), null, "123", "4567890", "1234")
		));
		final var address = this.addressMapper.toEntity(addressDto);
		assertNotNull(address);
		assertEquals(addressDto.id(), address.getId());
		assertEquals(addressDto.country(), address.getCountry());
		assertEquals(addressDto.zipCode(), address.getZipCode());
		assertEquals(addressDto.city(), address.getCity());
		assertEquals(addressDto.street(), address.getStreet());
		assertEquals(3, address.getContacts().size());
	}

	@Test
	public void test_map_entity_with_no_contacts_to_dto() {
		final var address = new Address(UUID.randomUUID(), "Country", "12345", "City", "Street", Set.of());
		final var addressDto = this.addressMapper.toDto(address);
		assertNotNull(addressDto);
		assertEquals(address.getId(), addressDto.id());
		assertEquals(address.getCountry(), addressDto.country());
		assertEquals(address.getZipCode(), addressDto.zipCode());
		assertEquals(address.getCity(), addressDto.city());
		assertEquals(address.getStreet(), addressDto.street());
		assertTrue(addressDto.contacts().isEmpty());
	}

	@Test
	public void test_map_dto_with_no_contacts_to_entity() {
		final var addressDto = new AddressDto(UUID.randomUUID(), "Country", "12345", "City", "Street", Set.of());
		final var address = this.addressMapper.toEntity(addressDto);
		assertNotNull(address);
		assertEquals(addressDto.id(), address.getId());
		assertEquals(addressDto.country(), address.getCountry());
		assertEquals(addressDto.zipCode(), address.getZipCode());
		assertEquals(addressDto.city(), address.getCity());
		assertEquals(addressDto.street(), address.getStreet());
		assertTrue(address.getContacts().isEmpty());
	}

	@Test
	public void test_mapping_null_entity_to_dto() {
		final var addressDto = this.addressMapper.toDto(null);
		assertNull(addressDto);
	}

	@Test
	public void test_mapping_null_AddressDto_to_entity() {
		final AddressDto addressDto = null;
		final var address = this.addressMapper.toEntity(addressDto);
		assertNull(address);
	}

	@Test
	public void test_map_addressDto_with_multiple_contacts_to_entity() {
		final var addressDto = new AddressDto(UUID.randomUUID(), "Country", "12345", "City", "Street", Set.of(
				new ContactDto(ContactType.EMAIL, UUID.randomUUID(), "email@example.com", null, null, null),
				new ContactDto(ContactType.MOBILE, UUID.randomUUID(), null, "123", "4567890", null),
				new ContactDto(ContactType.LANDLINE, UUID.randomUUID(), null, "123", "4567890", "1234")
		));
		final var address = this.addressMapper.toEntity(addressDto);
		assertNotNull(address);
		assertEquals(addressDto.id(), address.getId());
		assertEquals(addressDto.country(), address.getCountry());
		assertEquals(addressDto.zipCode(), address.getZipCode());
		assertEquals(addressDto.city(), address.getCity());
		assertEquals(addressDto.street(), address.getStreet());
		assertEquals(3, address.getContacts().size());
	}

	@Test
	public void test_mapping_entity_with_unknown_contact_type_to_dto() {
		final var address = new Address(UUID.randomUUID(), "Country", "12345", "City", "Street", Set.of(new UnknownContact(UUID.randomUUID())));
		assertThrows(IllegalStateException.class, () -> this.addressMapper.toDto(address));
	}

	@Test
	public void test_mapping_entity_with_null_contacts_to_dto_() {
		assertThrows(IllegalArgumentException.class, () -> new Address(UUID.randomUUID(), "Country", "12345", "City", "Street", null));
	}

	@Test
	public void test_mapping_addressDto_with_null_contacts_to_entity_with_empty_contacts() {
		final var addressDto = new AddressDto(UUID.randomUUID(), "Country", "12345", "City", "Street", null);
		final var address = this.addressMapper.toEntity(addressDto);
		assertNotNull(address);
		assertEquals(addressDto.id(), address.getId());
		assertEquals(addressDto.country(), address.getCountry());
		assertEquals(addressDto.zipCode(), address.getZipCode());
		assertEquals(addressDto.city(), address.getCity());
		assertEquals(addressDto.street(), address.getStreet());
		assertTrue(address.getContacts().isEmpty());
	}

	@Test
	public void test_mapping_entity_with_null_contacts_to_dto() {
		final Set<Contact> contacts = new HashSet<>();
		contacts.add(null);
		contacts.add(null);
		contacts.add(null);
		final var address = new Address(UUID.randomUUID(), "Country", "12345", "City", "Street", contacts);
		assertThrows(IllegalStateException.class, () -> this.addressMapper.toDto(address));
	}

	@Test
	public void test_mapping_addressDto_with_null_contacts_to_entity_with_non_null_contacts() {
		final var contactDtos = new HashSet<ContactDto>();
		contactDtos.add(new ContactDto(ContactType.EMAIL, UUID.randomUUID(), "email@example.com", null, null, null));
		contactDtos.add(null);
		contactDtos.add(new ContactDto(ContactType.MOBILE, UUID.randomUUID(), null, "123", "4567890", null));
		contactDtos.add(null);
		contactDtos.add(new ContactDto(ContactType.LANDLINE, UUID.randomUUID(), null, "123", "4567890", "1234"));
		contactDtos.add(null);
		final var addressDto = new AddressDto(UUID.randomUUID(), "Country", "12345", "City", "Street", contactDtos);
		final var address = this.addressMapper.toEntity(addressDto);
		assertNotNull(address);
		assertEquals(addressDto.id(), address.getId());
		assertEquals(addressDto.country(), address.getCountry());
		assertEquals(addressDto.zipCode(), address.getZipCode());
		assertEquals(addressDto.city(), address.getCity());
		assertEquals(addressDto.street(), address.getStreet());
		assertEquals(3, address.getContacts().size());
	}

	@Disabled
	@Test
	public void test_mapping_entity_with_duplicate_contacts_to_dto() {
		final var emailAddress1 = new EmailAddress(UUID.randomUUID(), "email@example.com");
		final var emailAddress2 = new EmailAddress(UUID.randomUUID(), "email@example.com");
		final var address = new Address(UUID.randomUUID(), "Country", "12345", "City", "Street", Set.of(emailAddress1, emailAddress2));
		final var addressDto = this.addressMapper.toDto(address);
		assertNotNull(addressDto);
		assertEquals(address.getId(), addressDto.id());
		assertEquals(address.getCountry(), addressDto.country());
		assertEquals(address.getZipCode(), addressDto.zipCode());
		assertEquals(address.getCity(), addressDto.city());
		assertEquals(address.getStreet(), addressDto.street());
		assertEquals(1, addressDto.contacts().size());
	}

	private static final class UnknownContact extends Contact {

		public UnknownContact(final UUID id) {
			super(id);
		}
	}
}
