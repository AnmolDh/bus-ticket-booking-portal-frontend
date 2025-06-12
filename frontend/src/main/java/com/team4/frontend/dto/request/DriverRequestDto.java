package com.team4.frontend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record DriverRequestDto(Integer id, @NotNull @Size(max = 20) String licenseNumber,
                               @NotNull @Size(max = 255) String name, @NotNull @Size(max = 15) String phone,
                               Integer officeId, Integer addressId) implements Serializable {
}