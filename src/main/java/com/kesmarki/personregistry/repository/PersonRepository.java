package com.kesmarki.personregistry.repository;

import com.kesmarki.personregistry.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
