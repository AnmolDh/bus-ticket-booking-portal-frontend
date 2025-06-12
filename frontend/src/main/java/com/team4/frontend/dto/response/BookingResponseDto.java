package com.team4.frontend.dto.response;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record BookingResponseDto(
        Integer id, TripResponseDto trip, @NotNull Integer seatNumber, String status
) implements Serializable {
}
