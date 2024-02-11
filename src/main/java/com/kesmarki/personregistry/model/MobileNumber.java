package com.kesmarki.personregistry.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;
import java.util.UUID;

@Entity
@DiscriminatorValue("mobile")
public class MobileNumber extends Contact {

	@NotBlank
	@Column(name = "prefix", length = 32)
	private String prefix;

	@NotBlank
	@Column(name = "number", length = 32)
	private String number;

	protected MobileNumber() {
	}

	public MobileNumber(final UUID id, final String prefix, final String number) {
		super(id);
		if (prefix == null || number == null) {
			throw new IllegalArgumentException();
		}
		this.prefix = prefix;
		this.number = number;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public String getNumber() {
		return this.number;
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
