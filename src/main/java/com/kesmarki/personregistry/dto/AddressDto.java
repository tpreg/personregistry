package com.kesmarki.personregistry.dto;

import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.kesmarki.personregistry.model.Address}
 */
public record AddressDto(UUID id, String country, String zipCode, String city, String street, List<ContactDto> contacts) {

}
