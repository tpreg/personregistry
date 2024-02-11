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
	private Set<Contact> contacts;

	protected Address() {
	}

	public Address(final String country, final String zipCode, final String city, final String street, final Set<Contact> contacts) {
		if (country == null || zipCode == null || city == null || street == null || contacts == null) {
			throw new IllegalArgumentException();
		}
		this.country = country;
		this.zipCode = zipCode;
		this.city = city;
		this.street = street;
		this.contacts = new HashSet<>(contacts);
	}

	public String getCountry() {
		return this.country;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public String getCity() {
		return this.city;
	}

	public String getStreet() {
		return this.street;
	}

	public Set<Contact> getContacts() {
		return this.contacts;
	}

	@Override
	public String toString() {
		return "Address{country='%s', zipCode='%s', city='%s', street='%s', contacts=%s}".formatted(this.country,
				this.zipCode, this.city, this.street, this.contacts);
	}
}
