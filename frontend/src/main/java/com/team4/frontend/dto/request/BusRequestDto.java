package com.team4.frontend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record BusRequestDto(
        Integer id,
        @NotNull @Size(max = 20) String registrationNumber,
        @NotNull Integer capacity,
        @NotNull @Size(max = 30) String type,
        Integer officeId
) implements Serializable {}
