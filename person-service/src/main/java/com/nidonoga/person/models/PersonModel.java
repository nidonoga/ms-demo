package com.nidonoga.person.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PERSON")
public class PersonModel {
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID personId;
    
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getPersonId() {
		return personId;
	}

	public void setPersonId(UUID personId) {
		this.personId = personId;
	}
}
