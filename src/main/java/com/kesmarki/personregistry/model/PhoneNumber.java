package com.kesmarki.personregistry.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("landline")
public class PhoneNumber extends Contact {
	@Column(name = "prefix", length = 32)
	private String prefix;

	@Column(name = "number", length = 32)
	private String number;

	@Column(name = "extension", length = 32)
	private String extension;

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

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(final String extension) {
		this.extension = extension;
	}
}