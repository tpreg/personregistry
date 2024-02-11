package com.kesmarki.personregistry.service;

import com.kesmarki.personregistry.dto.PersonDto;
import com.kesmarki.personregistry.exception.ResourceNotFoundException;
import com.kesmarki.personregistry.mapper.AddressMapper;
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
	private final AddressMapper addressMapper;

	public PersonServiceImpl(final PersonRepository personRepository, final PersonMapper personMapper, final AddressMapper addressMapper) {
		this.personRepository = personRepository;
		this.personMapper = personMapper;
		this.addressMapper = addressMapper;
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

	@Override
	public PersonDto update(final UUID id, final PersonDto personDto) {
		final var person = this.personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: %s".formatted(id)));
		person.update(personDto.fullName(), personDto.dateOfBirth(), personDto.birthplace(), this.addressMapper.toEntity(personDto.physicalAddress()), this.addressMapper.toEntity(personDto.residentialAddress()));
		final var personUpdated = this.personRepository.save(person);
		return this.personMapper.toDto(personUpdated);
	}

}
