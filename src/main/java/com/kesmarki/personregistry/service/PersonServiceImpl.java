package com.kesmarki.personregistry.service;

import com.kesmarki.personregistry.dto.PersonDto;
import com.kesmarki.personregistry.mapper.PersonMapper;
import com.kesmarki.personregistry.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;
	private final PersonMapper personMapper;

	public PersonServiceImpl(final PersonRepository personRepository, final PersonMapper personMapper) {
		this.personRepository = personRepository;
		this.personMapper = personMapper;
	}

	@Override
	public List<PersonDto> list() {
		return this.personRepository.findAll().stream().map(this.personMapper::toDto).toList();
	}

	@Override
	public PersonDto save(final PersonDto personDto) {
		final var entity = this.personMapper.toEntity(personDto);
		final var person = this.personRepository.save(entity);
		return this.personMapper.toDto(person);
	}

	@Override
	public Optional<PersonDto> get(final UUID id) {
		return Optional.ofNullable(id).flatMap(uuid -> this.personRepository.findById(uuid).map(this.personMapper::toDto));
	}

	@Override
	public void delete(final UUID id) {
		this.personRepository.deleteById(id);
	}

}
