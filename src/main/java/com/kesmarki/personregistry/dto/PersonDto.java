package com.kesmarki.personregistry.dto;

import java.util.UUID;

/**
 * DTO for {@link com.kesmarki.personregistry.model.Person}
 */
public record PersonDto(UUID id, AddressDto physicalAddress, AddressDto residentialAddress) {
}