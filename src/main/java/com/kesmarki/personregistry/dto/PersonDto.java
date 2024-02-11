package com.kesmarki.personregistry.dto;

import java.time.LocalDate;

/**
 * DTO for {@link com.kesmarki.personregistry.model.Person}
 */
public record PersonDto(String fullName, LocalDate dateOfBirth, String birthplace, AddressDto physicalAddress,
                        AddressDto residentialAddress
) {
}
