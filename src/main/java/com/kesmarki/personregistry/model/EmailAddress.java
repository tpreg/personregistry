package com.kesmarki.personregistry.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;

import java.util.regex.Pattern;

@Entity
@DiscriminatorValue("email_address")
public class EmailAddress extends Contact {

	public static final String OWASP_EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
	@Email(regexp = OWASP_EMAIL_REGEX)
	@Column(name = "email", length = 64)
	private String email;


	public EmailAddress() {
		super();
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		if (Pattern.matches(OWASP_EMAIL_REGEX, email)) {
			this.email = email;
		} else {
			throw new IllegalArgumentException("Invalid email address: %s".formatted(email));
		}
	}
}
