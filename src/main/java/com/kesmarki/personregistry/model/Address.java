package com.kesmarki.personregistry.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", nullable = false)
	private UUID id;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private List<Contact> contacts;

	public UUID getId() {
		return this.id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public List<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(final List<Contact> contacts) {
		this.contacts = contacts;
	}
}
