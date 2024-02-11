package com.kesmarki.personregistry.service;

import com.kesmarki.personregistry.dto.PersonDto;
import com.kesmarki.personregistry.mapper.PersonMapper;
import com.kesmarki.personregistry.model.Person;
import com.kesmarki.personregistry.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;
	private final PersonMapper personMapper;

	public PersonServiceImpl(final PersonRepository personRepository, final PersonMapper personMapper) {
		this.personRepository = personRepository;
		this.personMapper = personMapper;
	}

	@Override
	public PersonDto save(final PersonDto personDto) {
		final Person person = this.personRepository.save(this.personMapper.toEntity(personDto));
		return this.personMapper.toDto(person);
	}

}
