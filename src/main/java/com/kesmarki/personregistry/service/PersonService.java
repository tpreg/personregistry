package com.kesmarki.personregistry.service;

import com.kesmarki.personregistry.dto.PersonDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {

	List<PersonDto> list();

	PersonDto save(PersonDto personDto);

	Optional<PersonDto> get(UUID id);

	void delete(UUID id);

}
