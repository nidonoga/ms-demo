package com.nidonoga.person.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nidonoga.person.models.PersonModel;

public interface PersonRepository extends JpaRepository<PersonModel, UUID> {

}
