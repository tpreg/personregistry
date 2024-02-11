package com.kesmarki.personregistry.mapper;

import com.kesmarki.personregistry.dto.ContactDto;
import com.kesmarki.personregistry.model.Contact;
import com.kesmarki.personregistry.model.ContactType;
import com.kesmarki.personregistry.model.EmailAddress;
import com.kesmarki.personregistry.model.MobileNumber;
import com.kesmarki.personregistry.model.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

	public ContactDto toDto(final Contact contact) {
		if (contact instanceof final EmailAddress emailAddress) {
			return new ContactDto(ContactType.EMAIL, emailAddress.getEmail(), null, null, null);
		}
		if (contact instanceof final MobileNumber mobileNumber) {
			return new ContactDto(ContactType.MOBILE, null, mobileNumber.getPrefix(), mobileNumber.getNumber(), null);
		}
		if (contact instanceof final PhoneNumber phoneNumber) {
			return new ContactDto(ContactType.LANDLINE, null, phoneNumber.getPrefix(), phoneNumber.getNumber(), phoneNumber.getExtension());
		}
		throw new IllegalStateException("Unknown contact type: %s".formatted(contact));
	}

	public Contact toEntity(final ContactDto contactDto) {
		if (contactDto == null) {
			return null;
		}
		if (contactDto.type() == ContactType.EMAIL) {
			return new EmailAddress(contactDto.email());
		}
		if (contactDto.type() == ContactType.MOBILE) {
			return new MobileNumber(contactDto.prefix(), contactDto.number());
		}
		if (contactDto.type() == ContactType.LANDLINE) {
			return new PhoneNumber(contactDto.prefix(), contactDto.number(), contactDto.extension());
		}
		throw new IllegalStateException("Unknown contact type: %s".formatted(contactDto.type()));
	}
}
