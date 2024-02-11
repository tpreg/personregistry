package com.kesmarki.personregistry.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("mobile")
public class MobileNumber extends Contact {

	@Column(name = "prefix", length = 32)
	private String prefix;

	@Column(name = "number", unique = true, length = 32)
	private String number;

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(final String prefix) {
		this.prefix = prefix;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}
}