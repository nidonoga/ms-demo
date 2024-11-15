package com.nidonoga.user.dtos;

import java.io.Serializable;

import com.nidonoga.user.enums.ValidationStatusEnum;

public record MessageValidationDto(String message, ValidationStatusEnum validationStatus) implements Serializable {

}
