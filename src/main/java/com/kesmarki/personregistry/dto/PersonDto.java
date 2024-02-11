package com.kesmarki.personregistry.dto;

import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link com.kesmarki.personregistry.model.Person}
 */
public record PersonDto(UUID id, String fullName, LocalDate dateOfBirth, String birthplace, AddressDto physicalAddress,
                        AddressDto residentialAddress
) {
}
