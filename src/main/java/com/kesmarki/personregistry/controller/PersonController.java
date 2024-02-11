package com.kesmarki.personregistry.controller;

import com.kesmarki.personregistry.dto.PersonDto;
import com.kesmarki.personregistry.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("persons")
public class PersonController {

	private final PersonService personService;

	public PersonController(@Autowired final PersonService personService) {
		this.personService = personService;
	}

	@GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
	public List<PersonDto> getPersons() {
		return this.personService.list();
	}

	@PostMapping(value = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDto> save(@RequestBody final PersonDto personDto) {
		return new ResponseEntity<>(this.personService.save(personDto), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDto> update(@PathVariable final UUID id, @RequestBody final PersonDto personDto) {
		return new ResponseEntity<>(this.personService.update(id, personDto), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDto> get(@PathVariable final UUID id) {
		return this.personService.get(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public void delete(@PathVariable final UUID id) {
		this.personService.delete(id);
	}

}
