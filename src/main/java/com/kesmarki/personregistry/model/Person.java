package com.kesmarki.personregistry.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

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

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(final LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBirthplace() {
		return this.birthplace;
	}

	public void setBirthplace(final String birthplace) {
		this.birthplace = birthplace;
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
