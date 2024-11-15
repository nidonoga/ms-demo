package com.nidonoga.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRecordDto(@NotBlank @Email String emailTo, @NotBlank String subject, String text) {

}
