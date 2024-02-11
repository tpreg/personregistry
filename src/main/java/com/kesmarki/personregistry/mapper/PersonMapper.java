package com.kesmarki.personregistry.mapper;

import com.kesmarki.personregistry.dto.PersonDto;
import com.kesmarki.personregistry.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

	private final AddressMapper addressMapper;

	public PersonMapper(final AddressMapper addressMapper) {
		this.addressMapper = addressMapper;
	}


	public PersonDto toDto(final Person person) {
		return new PersonDto(person.getId(), this.addressMapper.toDto(person.getPhysicalAddress()), this.addressMapper.toDto(person.getResidentialAddress()));
	}

	public Person toEntity(final PersonDto personDto) {
		final var person = new Person();
		person.setId(personDto.id());
		if (personDto.physicalAddress() != null) {
			person.setPhysicalAddress(this.addressMapper.toEntity(personDto.physicalAddress()));
		}
		if (personDto.residentialAddress() != null) {
			person.setResidentialAddress(this.addressMapper.toEntity(personDto.residentialAddress()));
		}
		return person;
	}
}
