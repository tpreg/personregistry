package com.kesmarki.personregistry.dto;

import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.kesmarki.personregistry.model.Address}
 */
public record AddressDto(UUID id, String country, String zipCode, String city, String street, Set<ContactDto> contacts) {

}
