package com.team4.frontend.dto.request;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


public record BookingRequestDto(Integer id, Integer tripId, @NotNull Integer seatNumber, String status) implements Serializable {
}