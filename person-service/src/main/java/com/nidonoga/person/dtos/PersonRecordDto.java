package com.nidonoga.person.dtos;

import java.io.Serializable;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record PersonRecordDto (UUID personId, @NotBlank String name) implements Serializable {

}
