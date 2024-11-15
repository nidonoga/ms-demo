package com.nidonoga.user.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(
		UUID id,
		@NotBlank String login, 
		@NotBlank @Email String email,
		@NotBlank String name,
		LocalDateTime registrationDate,
		LocalDateTime updateDate) implements Serializable {

}
