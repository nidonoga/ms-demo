package com.nidonoga.email.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDto(@NotBlank @Email String emailTo, @NotBlank String subject, String text) {

}
