package com.nidonoga.email.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nidonoga.email.dtos.EmailDto;
import com.nidonoga.email.services.EmailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("email-service")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping
	public ResponseEntity<EmailDto> sendEmail(@RequestBody @Valid EmailDto dto) {
		dto = emailService.sendEmail(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
}
