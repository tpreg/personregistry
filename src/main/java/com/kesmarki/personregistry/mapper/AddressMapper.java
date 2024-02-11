package com.kesmarki.personregistry.mapper;

import com.kesmarki.personregistry.dto.AddressDto;
import com.kesmarki.personregistry.model.Address;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
public class AddressMapper {

	private final ContactMapper contactMapper;

	public AddressMapper(final ContactMapper contactMapper) {
		this.contactMapper = contactMapper;
	}

	public AddressDto toDto(final Address address) {
		if (address == null) {
			return null;
		}
		if (address.getContacts() == null) {
			return new AddressDto(address.getId(), address.getCountry(), address.getZipCode(), address.getCity(), address.getStreet(), Set.of());
		}
		return new AddressDto(address.getId(), address.getCountry(), address.getZipCode(), address.getCity(), address.getStreet(), address.getContacts().stream().map(this.contactMapper::toDto).collect(toSet()));

	}

	public Address toEntity(final AddressDto addressDto) {
		if (addressDto == null) {
			return null;
		}
		final var address = new Address();
		address.setId(addressDto.id());
		address.setCountry(addressDto.country());
		address.setZipCode(addressDto.zipCode());
		address.setCity(addressDto.city());
		address.setStreet(addressDto.street());
		if (addressDto.contacts() != null) {
			address.setContacts(addressDto.contacts().stream().filter(Objects::nonNull).map(this.contactMapper::toEntity).collect(toSet()));
		}
		return address;
	}
}
