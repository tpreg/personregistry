package com.kesmarki.personregistry.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;

import java.util.Objects;
import java.util.regex.Pattern;

@Entity
@DiscriminatorValue("email_address")
public class EmailAddress extends Contact {

	public static final String OWASP_EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
	@Email(regexp = OWASP_EMAIL_REGEX)
	@Column(name = "email", length = 64)
	private String email;

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

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass() || !super.equals(o)) {
			return false;
		}
		final EmailAddress that = (EmailAddress) o;
		return Objects.equals(this.email, that.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), this.email);
	}

	@Override
	public String toString() {
		return "EmailAddress{id='%s', email='%s'}".formatted(getId(), this.email);
	}
}
