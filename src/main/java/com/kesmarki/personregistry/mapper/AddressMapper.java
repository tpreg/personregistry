package com.kesmarki.personregistry.mapper;

import com.kesmarki.personregistry.dto.AddressDto;
import com.kesmarki.personregistry.model.Address;
import org.springframework.stereotype.Component;

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
		return new AddressDto(address.getId(), address.getCountry(), address.getZipCode(), address.getCity(), address.getCity(), address.getContacts().stream().map(this.contactMapper::toDto).toList());
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
		address.setContacts(addressDto.contacts().stream().map(this.contactMapper::toEntity).toList());
		return address;
	}
}
