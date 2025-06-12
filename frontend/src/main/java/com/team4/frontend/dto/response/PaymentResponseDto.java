package com.team4.frontend.dto.response;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record PaymentResponseDto(
        Integer id,
        @NotNull BookingResponseDto booking,
        CustomerResponseDto customer,
        BigDecimal amount,
        Instant paymentDate,
        String paymentStatus
) implements Serializable {
}