package com.kesmarki.personregistry.controller;

import com.kesmarki.personregistry.dto.PersonDto;
import com.kesmarki.personregistry.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("persons")
public class PersonController {

	private final PersonService personService;

	public PersonController(@Autowired final PersonService personService) {
		this.personService = personService;
	}

	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<PersonDto> save(@RequestBody final PersonDto personDto) {
		return new ResponseEntity<>(this.personService.save(personDto), HttpStatus.CREATED);
	}

}
