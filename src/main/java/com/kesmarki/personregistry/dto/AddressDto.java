package com.kesmarki.personregistry.dto;

import java.util.Set;

/**
 * DTO for {@link com.kesmarki.personregistry.model.Address}
 */
public record AddressDto(String country, String zipCode, String city, String street, Set<ContactDto> contacts) {

}
