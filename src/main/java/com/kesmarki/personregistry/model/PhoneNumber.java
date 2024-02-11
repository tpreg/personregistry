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

	protected PhoneNumber() {
	}

	public PhoneNumber(final String prefix, final String number, final String extension) {
		super();
		if (prefix == null || number == null || extension == null) {
			throw new IllegalArgumentException();
		}
		this.prefix = prefix;
		this.number = number;
		this.extension = extension;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public String getNumber() {
		return this.number;
	}

	public String getExtension() {
		return this.extension;
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
		return "PhoneNumber{prefix='%s', number='%s', extension='%s'}".formatted(this.prefix, this.number, this.extension);
	}
}
