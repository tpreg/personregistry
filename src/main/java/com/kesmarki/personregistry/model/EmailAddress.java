package com.kesmarki.personregistry.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;

@Entity
@DiscriminatorValue("email_address")
public class EmailAddress extends Contact {

	@Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")
	// OWASP
	@Column(name = "email", length = 64)
	private String email;


	public EmailAddress() {
		super();
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}
}