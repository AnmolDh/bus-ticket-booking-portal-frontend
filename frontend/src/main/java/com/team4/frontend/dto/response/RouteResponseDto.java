package com.team4.frontend.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record RouteResponseDto(
        Integer id, @NotNull @Size(max = 255) String fromCity, @NotNull @Size(max = 255) String toCity,
        Integer breakPoints, Integer duration
) implements Serializable {
}
