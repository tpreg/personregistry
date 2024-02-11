package com.kesmarki.personregistry.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "zip_code", nullable = false)
	private String zipCode;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "street", nullable = false)
	private String street;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private Set<Contact> contacts = new HashSet<>();

	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(final String street) {
		this.street = street;
	}

	public Set<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(final Set<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "Address{id='%s', country='%s', zipCode='%s', city='%s', street='%s', contacts=%s}".formatted(getId(), this.country,
				this.zipCode, this.city, this.street, this.contacts);
	}
}
