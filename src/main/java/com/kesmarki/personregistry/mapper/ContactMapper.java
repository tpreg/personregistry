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
			return new ContactDto(ContactType.EMAIL, emailAddress.getId(), emailAddress.getEmail(), null, null, null);
		}
		if (contact instanceof final MobileNumber mobileNumber) {
			return new ContactDto(ContactType.MOBILE, mobileNumber.getId(), null, mobileNumber.getPrefix(), mobileNumber.getNumber(), null);
		}
		if (contact instanceof final PhoneNumber phoneNumber) {
			return new ContactDto(ContactType.LANDLINE, phoneNumber.getId(), null, phoneNumber.getPrefix(), phoneNumber.getNumber(), phoneNumber.getExtension());
		}
		throw new IllegalStateException("Unknown contact type: %s".formatted(contact.getClass()));
	}

	public Contact toEntity(final ContactDto contactDto) {
		if (contactDto == null) {
			return null;
		}
		if (contactDto.type() == ContactType.EMAIL) {
			final EmailAddress emailAddress = new EmailAddress();
			emailAddress.setId(contactDto.id());
			emailAddress.setEmail(contactDto.email());
			return emailAddress;
		}
		if (contactDto.type() == ContactType.MOBILE) {
			final MobileNumber mobileNumber = new MobileNumber();
			mobileNumber.setId(contactDto.id());
			mobileNumber.setPrefix(contactDto.prefix());
			mobileNumber.setNumber(contactDto.number());
			return mobileNumber;
		}
		if (contactDto.type() == ContactType.LANDLINE) {
			final PhoneNumber phoneNumber = new PhoneNumber();
			phoneNumber.setId(contactDto.id());
			phoneNumber.setPrefix(contactDto.prefix());
			phoneNumber.setNumber(contactDto.number());
			phoneNumber.setExtension(contactDto.extension());
			return phoneNumber;
		}
		throw new IllegalStateException("Unknown contact type: %s".formatted(contactDto.type()));
	}
}
