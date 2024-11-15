package com.nidonoga.user.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nidonoga.user.dtos.MessageValidationDto;
import com.nidonoga.user.dtos.UserRecordDto;
import com.nidonoga.user.services.UserService;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping("user-service")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Object> createUser(@RequestBody @Valid UserRecordDto userRecordDto) {
		List<MessageValidationDto> validations = userService.validateNewUser(userRecordDto);
		if(!validations.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(validations);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRecordDto));
	}
	
	@GetMapping
	public ResponseEntity<Page<UserRecordDto>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOne(@PathVariable UUID id) {
		Optional<UserRecordDto> userRecordOptional = userService.findById(id);
		if(!userRecordOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado."); 
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(userRecordOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable UUID id) {
		Optional<UserRecordDto> userRecordOptional = userService.findById(id);
		if(!userRecordOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado."); 
		}
		
		userService.delete(userRecordOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído.");
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid UserRecordDto dto) {
		Optional<UserRecordDto> userRecordOptional = userService.findById(id);
		if(!userRecordOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado."); 
		}
				
		return ResponseEntity.status(HttpStatus.OK).body(userService.update(userRecordOptional.get(), dto));
	}
}
