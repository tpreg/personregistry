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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class PersonMapperTest {

	@Autowired
	private PersonMapper personMapper;


	@Test
	void testToDTO() {
		final var address = new Address("Magyarország", "1136", "Budapest", "Pannónia utca 23.", Set.of());
		final var person = new Person("Virág Cserepes", LocalDate.of(1991, 10, 2), "Hungary", address, null);
		final var dto = this.personMapper.toDto(person);
		assertEquals(person.getFullName(), dto.fullName());
		assertEquals(address.getContacts().size(), dto.physicalAddress().contacts().size());
	}

	@Test
	void testToEntity() {
		final var addressDTO = new AddressDto("Magyarország", "1136", "Budapest", "Pannónia utca 23.", Set.of());
		final var dto = new PersonDto("Virág Cserepes", LocalDate.of(1991, 10, 2), "Hungary", addressDTO, null);
		final var person = this.personMapper.toEntity(dto);
		assertEquals(dto.fullName(), person.getFullName());
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
		final var dto = new PersonDto("Virág Cserepes", LocalDate.of(1991, 10, 2), "Hungary", null, null);
		final var person = this.personMapper.toEntity(dto);
		final var convertedDTO = this.personMapper.toDto(person);
		assertEquals(dto.fullName(), convertedDTO.fullName());
	}

	@Test
	void testInconsistentData() {
		final var person = new Person("Virág Cserepes", LocalDate.of(1991, 10, 2), "Hungary", null, null);
		final var dto = new PersonDto("Klaudia Cserepes", LocalDate.of(1991, 10, 2), "Hungary", null, null);
		final var convertedPerson = this.personMapper.toEntity(dto);
		assertNotEquals(person.getFullName(), convertedPerson.getFullName());
	}

	@Test
	void testEqualityCheck() {
		final var person = new Person("Virág Cserepes", LocalDate.of(1991, 10, 2), "Hungary", null, null);
		final var dto = this.personMapper.toDto(person);
		assertEquals(person.getFullName(), dto.fullName());
	}

}


