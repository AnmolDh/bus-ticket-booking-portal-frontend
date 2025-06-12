package com.team4.frontend.dto.request;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record PaymentRequestDto(
        Integer id,
        @NotNull BookingRequestDto booking,
        Integer customerId,
        BigDecimal amount,
        Instant paymentDate,
        String paymentStatus
) implements Serializable {}
