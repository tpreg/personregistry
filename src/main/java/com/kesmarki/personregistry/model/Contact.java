package com.kesmarki.personregistry.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "contact")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "contact_type", discriminatorType = DiscriminatorType.STRING)
public class Contact extends BaseEntity {

	protected Contact() {
	}

	public Contact(final UUID id) {
		super(id);
	}

	@Override
	public String toString() {
		return "Contact{id='%s'}".formatted(getId());
	}

}
