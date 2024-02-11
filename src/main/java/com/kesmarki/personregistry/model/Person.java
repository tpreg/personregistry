package com.kesmarki.personregistry.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person extends BaseEntity {

	@Column(name = "full_name", nullable = false)
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
	}

	public Person(final String fullName, final LocalDate dateOfBirth, final String birthplace, final Address physicalAddress, final Address residentialAddress) {
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

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass() || !super.equals(o)) {
			return false;
		}
		final Person person = (Person) o;
		return Objects.equals(this.fullName, person.fullName) && Objects.equals(this.dateOfBirth, person.dateOfBirth) && Objects.equals(this.birthplace, person.birthplace) && Objects.equals(this.physicalAddress, person.physicalAddress) && Objects.equals(this.residentialAddress, person.residentialAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), this.fullName, this.dateOfBirth, this.birthplace, this.physicalAddress, this.residentialAddress);
	}

	@Override
	public String toString() {
		return "Person{fullName='%s', dateOfBirth=%s, birthplace='%s', physicalAddress=%s, residentialAddress=%s}".formatted(this.fullName, this.dateOfBirth, this.birthplace, this.physicalAddress, this.residentialAddress);
	}
}
