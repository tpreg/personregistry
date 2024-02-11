package com.kesmarki.personregistry.dto;

import com.kesmarki.personregistry.model.ContactType;

/**
 * DTO for {@link com.kesmarki.personregistry.model.Contact}
 */
public record ContactDto(ContactType type, String email, String prefix, String number, String extension) {
}
