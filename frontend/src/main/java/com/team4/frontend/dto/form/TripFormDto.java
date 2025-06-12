package com.team4.frontend.dto.form;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TripFormDto {
    private Integer id;
    private Integer routeId;
    private Integer busId;
    private Integer driver1Id;
    private Integer driver2Id;
    private Integer boardingAddressId;
    private Integer droppingAddressId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer availableSeats;
    private BigDecimal fare;
    private LocalDate tripDate;

}
