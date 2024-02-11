package com.kesmarki.personregistry.mapper;

import com.kesmarki.personregistry.dto.PersonDto;
import com.kesmarki.personregistry.model.Person;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonMapper {

	private final AddressMapper addressMapper;

	public PersonMapper(final AddressMapper addressMapper) {
		this.addressMapper = addressMapper;
	}


	public PersonDto toDto(final Person person) {
		if (person == null) {
			return null;
		}
		return new PersonDto(person.getFullName(), person.getDateOfBirth(), person.getBirthplace(), this.addressMapper.toDto(person.getPhysicalAddress()), this.addressMapper.toDto(person.getResidentialAddress()));
	}

	public Person toEntity(final PersonDto personDto) {
		return new Person(
				personDto.fullName(),
				personDto.dateOfBirth(),
				personDto.birthplace(),
				Optional.ofNullable(personDto.physicalAddress()).map(this.addressMapper::toEntity).orElse(null),
				Optional.ofNullable(personDto.residentialAddress()).map(this.addressMapper::toEntity).orElse(null));
	}
}
