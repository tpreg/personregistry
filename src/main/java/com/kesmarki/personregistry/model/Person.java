package com.kesmarki.personregistry.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", nullable = false)
	private UUID id;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Address physicalAddress;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Address residentialAddress;

	public UUID getId() {
		return this.id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public Address getPhysicalAddress() {
		return this.physicalAddress;
	}

	public void setPhysicalAddress(final Address physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public Address getResidentialAddress() {
		return this.residentialAddress;
	}

	public void setResidentialAddress(final Address residentialAddress) {
		this.residentialAddress = residentialAddress;
	}


}
