package com.kesmarki.personregistry.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

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
		if (prefix == null) {
			throw new IllegalArgumentException("prefix is null");
		}
		this.prefix = prefix;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(final String number) {
		if (number == null) {
			throw new IllegalArgumentException("number is null");
		}
		this.number = number;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(final String extension) {
		if (extension == null) {
			throw new IllegalArgumentException("extension is null");
		}
		this.extension = extension;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass() || !super.equals(o)) {
			return false;
		}
		final PhoneNumber that = (PhoneNumber) o;
		return Objects.equals(this.prefix, that.prefix) && Objects.equals(this.number, that.number) && Objects.equals(this.extension, that.extension);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), this.prefix, this.number, this.extension);
	}

	@Override
	public String toString() {
		return "PhoneNumber{id='%s', prefix='%s', number='%s', extension='%s'}".formatted(getId(), this.prefix, this.number, this.extension);
	}
}
