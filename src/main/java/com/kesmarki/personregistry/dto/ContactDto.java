package com.kesmarki.personregistry.dto;

import com.kesmarki.personregistry.model.ContactType;

import java.util.UUID;

/**
 * DTO for {@link com.kesmarki.personregistry.model.Contact}
 */
public record ContactDto(ContactType type, UUID id, String email, String prefix, String number, String extension) {
}
