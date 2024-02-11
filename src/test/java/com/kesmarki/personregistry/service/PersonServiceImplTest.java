package com.kesmarki.personregistry.service;

import com.kesmarki.personregistry.dto.PersonDto;
import com.kesmarki.personregistry.mapper.PersonMapper;
import com.kesmarki.personregistry.model.Person;
import com.kesmarki.personregistry.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
		when(this.personRepository.findAll()).thenReturn(List.of(person));
		final var result = this.personService.list();
		assertEquals(1, result.size());
		assertEquals(UUID.fromString("0c601a41-46a0-4454-aef8-117cdc891479"), person.getId());
		assertEquals("John Doe", person.getFullName());
		assertEquals("New York", person.getBirthplace());
		assertEquals(LocalDate.of(1990, 1, 1), person.getDateOfBirth());
		assertEquals(null, person.getPhysicalAddress());
		assertEquals(null, person.getResidentialAddress());
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
		when(this.personRepository.findById(id)).thenReturn(Optional.of(person));
		when(this.personMapper.toDto(person)).thenReturn(personDto);
		final var result = this.personService.get(id);
		assertTrue(result.isPresent());
		assertEquals(UUID.fromString("0c601a41-46a0-4454-aef8-117cdc891479"), personDto.id());
		assertEquals("John Doe", personDto.fullName());
		assertEquals("New York", personDto.birthplace());
		assertEquals(LocalDate.of(1990, 1, 1), personDto.dateOfBirth());
		assertEquals(null, personDto.physicalAddress());
		assertEquals(null, personDto.residentialAddress());
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
		return new PersonDto(UUID.fromString("0c601a41-46a0-4454-aef8-117cdc891479"), "John Doe", LocalDate.of(1990, 1, 1), "New York", null, null);
	}

	private Person createPersonEntity() {
		final var person = new Person();
		person.setId(UUID.fromString("0c601a41-46a0-4454-aef8-117cdc891479"));
		person.setFullName("John Doe");
		person.setDateOfBirth(LocalDate.of(1990, 1, 1));
		person.setBirthplace("New York");
		person.setPhysicalAddress(null);
		person.setResidentialAddress(null);
		return person;
	}
}
