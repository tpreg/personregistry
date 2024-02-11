package com.kesmarki.personregistry.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "contact")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "contact_type", discriminatorType = DiscriminatorType.STRING)
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", nullable = false)
	private UUID id;

	public UUID getId() {
		return this.id;
	}

	public void setId(final UUID id) {
		if (id == null) {
			throw new IllegalArgumentException("id is null");
		}
		this.id = id;
	}

	@Override
	public String toString() {
		return "Contact{id='%s'}".formatted(this.id);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Contact contact = (Contact) o;
		return Objects.equals(this.id, contact.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}
}
