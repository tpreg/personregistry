package com.kesmarki.personregistry.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person extends BaseEntity {

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;

	@Column(name = "birthplace", nullable = false)
	private String birthplace;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Address physicalAddress;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Address residentialAddress;

	protected Person() {
		super();
	}

	public Person(final UUID id, final String fullName, final LocalDate dateOfBirth, final String birthplace, final Address physicalAddress, final Address residentialAddress) {
		super(id);
		if (fullName == null || dateOfBirth == null || birthplace == null) {
			throw new IllegalArgumentException();
		}
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.birthplace = birthplace;
		this.physicalAddress = physicalAddress;
		this.residentialAddress = residentialAddress;
	}

	public String getFullName() {
		return this.fullName;
	}

	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	public String getBirthplace() {
		return this.birthplace;
	}

	public Address getPhysicalAddress() {
		return this.physicalAddress;
	}

	public Address getResidentialAddress() {
		return this.residentialAddress;
	}

}
