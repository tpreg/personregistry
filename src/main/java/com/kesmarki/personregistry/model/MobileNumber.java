package com.kesmarki.personregistry.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@DiscriminatorValue("mobile")
public class MobileNumber extends Contact {

	@NotBlank
	@Column(name = "prefix", nullable = false, length = 32)
	private String prefix;

	@NotBlank
	@Column(name = "number", nullable = false, length = 32)
	private String number;

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

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass() || !super.equals(o)) {
			return false;
		}
		final MobileNumber that = (MobileNumber) o;
		return Objects.equals(this.prefix, that.prefix) && Objects.equals(this.number, that.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), this.prefix, this.number);
	}

	@Override
	public String toString() {
		return "MobileNumber{id='%s', prefix='%s', number='%s'}".formatted(getId(), this.prefix, this.number);
	}
}
