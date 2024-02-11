package com.kesmarki.personregistry.service;

import com.kesmarki.personregistry.dto.AddressDto;
import com.kesmarki.personregistry.dto.ContactDto;
import com.kesmarki.personregistry.dto.PersonDto;
import com.kesmarki.personregistry.mapper.PersonMapper;
import com.kesmarki.personregistry.model.Address;
import com.kesmarki.personregistry.model.ContactType;
import com.kesmarki.personregistry.model.EmailAddress;
import com.kesmarki.personregistry.model.Person;
import com.kesmarki.personregistry.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class PersonServiceImplTest {

	private final PersonRepository personRepository = Mockito.mock(PersonRepository.class);

	private final PersonMapper personMapper = Mockito.mock(PersonMapper.class);

	private final PersonService personService = new PersonServiceImpl(this.personRepository, this.personMapper);

	@Test
	public void testList() {
		final var person = createPersonEntity();
		final var physicalAddressEntity = createPhysicalAddressEntity();
		when(this.personRepository.findAll()).thenReturn(List.of(person));
		final var result = this.personService.list();
		assertEquals(1, result.size());
		assertEquals(UUID.fromString("0c601a41-46a0-4454-aef8-117cdc891479"), person.getId());
		assertEquals("John Doe", person.getFullName());
		assertEquals("New York", person.getBirthplace());
		assertEquals(LocalDate.of(1990, 1, 1), person.getDateOfBirth());
		assertEquals(physicalAddressEntity, person.getPhysicalAddress());
		assertNull(person.getResidentialAddress());
	}

	@Test
	public void testSave() {
		final var personDto = createPersonDto();
		final var person = createPersonEntity();
		when(this.personRepository.save(any())).thenReturn(person);
		when(this.personMapper.toDto(person)).thenReturn(personDto);
		when(this.personMapper.toEntity(personDto)).thenReturn(person);
		final var result = this.personService.save(personDto);
		assertEquals(personDto, result);
	}

	@Test
	public void testGet() {
		final var id = UUID.randomUUID();
		final var person = createPersonEntity();
		final var personDto = createPersonDto();
		final var physicalAddressDto = createPhysicalAddressDto();
		when(this.personRepository.findById(id)).thenReturn(Optional.of(person));
		when(this.personMapper.toDto(person)).thenReturn(personDto);
		final var result = this.personService.get(id);
		assertTrue(result.isPresent());
		assertEquals(UUID.fromString("0c601a41-46a0-4454-aef8-117cdc891479"), personDto.id());
		assertEquals("John Doe", personDto.fullName());
		assertEquals("New York", personDto.birthplace());
		assertEquals(LocalDate.of(1990, 1, 1), personDto.dateOfBirth());
		assertEquals(physicalAddressDto, personDto.physicalAddress());
		assertNull(personDto.residentialAddress());
	}

	@Test
	public void testGetWhenNotExists() {
		final var id = UUID.randomUUID();
		when(this.personRepository.findById(id)).thenReturn(Optional.empty());
		final var result = this.personService.get(id);
		assertFalse(result.isPresent());
	}

	@Test
	public void testDelete() {
		final var id = UUID.randomUUID();
		this.personService.delete(id);
		verify(this.personRepository).deleteById(id);
	}

	private PersonDto createPersonDto() {
		return new PersonDto(UUID.fromString("0c601a41-46a0-4454-aef8-117cdc891479"),
				"John Doe",
				LocalDate.of(1990, 1, 1),
				"New York",
				createPhysicalAddressDto(),
				null);
	}

	private Person createPersonEntity() {
		return new Person(UUID.fromString("0c601a41-46a0-4454-aef8-117cdc891479"),
				"John Doe",
				LocalDate.of(1990, 1, 1),
				"New York",
				createPhysicalAddressEntity(),
				null);
	}

	private Address createPhysicalAddressEntity() {
		return new Address(UUID.fromString("c4c92a2a-9fd4-4c6c-8b48-c10b82e3a4e2"),
				"USA",
				"12345",
				"New York",
				"Street 123",
				Set.of(createEmailAddressContactEntity()));
	}

	private EmailAddress createEmailAddressContactEntity() {
		return new EmailAddress(UUID.fromString("663473dd-8f60-4747-8763-4d9862e475e4"), "test@example.com");
	}

	private AddressDto createPhysicalAddressDto() {
		return new AddressDto(UUID.fromString("c4c92a2a-9fd4-4c6c-8b48-c10b82e3a4e2"),
				"USA",
				"12345",
				"New York",
				"Street 123",
				Set.of(createEmailAddressContactDto()));
	}

	private ContactDto createEmailAddressContactDto() {
		return new ContactDto(ContactType.EMAIL,
				UUID.fromString("663473dd-8f60-4747-8763-4d9862e475e4"),
				"test@example.com",
				null,
				null,
				null);
	}
}
