package com.nidonoga.person.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nidonoga.person.dtos.PersonRecordDto;
import com.nidonoga.person.dtos.TestApiDto;
import com.nidonoga.person.services.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("person-service")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private Environment environment;
	
	@PostMapping
	public ResponseEntity<PersonRecordDto> createPerson(@RequestBody @Valid PersonRecordDto dto) {
		dto = personService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@GetMapping("/apiTest")
	public ResponseEntity<TestApiDto> allUsers() {
		TestApiDto apiTest = new TestApiDto(this.getClass().getSimpleName(), environment.getProperty("local.server.port"), null);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiTest);
	}

}
