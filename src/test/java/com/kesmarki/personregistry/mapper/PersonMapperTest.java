package com.kesmarki.personregistry.mapper;

import com.kesmarki.personregistry.dto.AddressDto;
import com.kesmarki.personregistry.dto.PersonDto;
import com.kesmarki.personregistry.model.Address;
import com.kesmarki.personregistry.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class PersonMapperTest {

	@Autowired
	private PersonMapper personMapper;


	@Test
	void testToDTO() {
		final var address = new Address(UUID.randomUUID(), "Magyarország", "1136", "Budapest", "Pannónia utca 23.", Set.of());
		final var person = new Person(UUID.randomUUID(), "Virág Cserepes", LocalDate.of(1991, 10, 2), "Hungary", address, null);
		final var dto = this.personMapper.toDto(person);
		assertEquals(person.getId(), dto.id());
		assertEquals(address.getId(), dto.physicalAddress().id());
		assertEquals(address.getContacts().size(), dto.physicalAddress().contacts().size());
	}

	@Test
	void testToEntity() {
		final var addressDTO = new AddressDto(UUID.randomUUID(), "Magyarország", "1136", "Budapest", "Pannónia utca 23.", Set.of());
		final var dto = new PersonDto(UUID.randomUUID(), "Virág Cserepes", LocalDate.of(1991, 10, 2), "Hungary", addressDTO, null);
		final var person = this.personMapper.toEntity(dto);
		assertEquals(dto.id(), person.getId());
		assertEquals(addressDTO.id(), person.getPhysicalAddress().getId());
		assertEquals(addressDTO.contacts().size(), person.getPhysicalAddress().getContacts().size());
	}

	@Test
	void testNullConversion() {
		final Person person = null;
		final var dto = this.personMapper.toDto(person);
		assertNull(dto);
	}

	@Test
	void testBidirectionalConversion() {
		final var dto = new PersonDto(UUID.randomUUID(), "Virág Cserepes", LocalDate.of(1991, 10, 2), "Hungary", null, null);
		final var person = this.personMapper.toEntity(dto);
		final var convertedDTO = this.personMapper.toDto(person);
		assertEquals(dto.id(), convertedDTO.id());
	}

	@Test
	void testInconsistentData() {
		final var person = new Person(UUID.randomUUID(), "Virág Cserepes", LocalDate.of(1991, 10, 2), "Hungary", null, null);
		final var dto = new PersonDto(UUID.randomUUID(), "Virág Cserepes", LocalDate.of(1991, 10, 2), "Hungary", null, null);
		final var convertedPerson = this.personMapper.toEntity(dto);
		assertNotEquals(person.getId(), convertedPerson.getId());
	}

	@Test
	void testEqualityCheck() {
		final var person = new Person(UUID.randomUUID(), "Virág Cserepes", LocalDate.of(1991, 10, 2), "Hungary", null, null);
		final var dto = this.personMapper.toDto(person);
		assertEquals(person.getId(), dto.id());
	}

}


