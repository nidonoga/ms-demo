package com.nidonoga.person.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nidonoga.person.dtos.PersonRecordDto;
import com.nidonoga.person.models.PersonModel;
import com.nidonoga.person.repositories.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Transactional
	public PersonRecordDto save(PersonRecordDto dto) {
		var model = new PersonModel();
		BeanUtils.copyProperties(dto, model);
		
		model = personRepository.save(model);
		return new PersonRecordDto(model.getPersonId(), model.getName());
	}

}
