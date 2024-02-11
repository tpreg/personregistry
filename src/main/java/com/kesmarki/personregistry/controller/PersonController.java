package com.kesmarki.personregistry.controller;

import com.kesmarki.personregistry.model.Person;
import com.kesmarki.personregistry.repository.PersonRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("persons")
public class PersonController {

	private final PersonRepository personRepository;

	public PersonController(final PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public Person save(@RequestBody final Person person) {
		return this.personRepository.save(person);
	}

}
