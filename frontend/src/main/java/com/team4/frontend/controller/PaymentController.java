package com.team4.frontend.controller;

import com.team4.frontend.client.ApiClient;
import com.team4.frontend.dto.request.BookingRequestDto;
import com.team4.frontend.dto.request.PaymentRequestDto;
import com.team4.frontend.dto.response.PaymentResponseDto;
import com.team4.frontend.dto.response.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final ApiClient apiClient;


    // Add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        BookingRequestDto bookingDto = new BookingRequestDto(
                null, null, 0, "Booked"
        );

        PaymentRequestDto dto = new PaymentRequestDto(
                null, bookingDto, null, null, null, ""
        );
        model.addAttribute("payment", dto);
        model.addAttribute("mode", "add");
        return "add-edit-payment";
    }

    // Edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        PaymentResponseDto resp = apiClient.getPaymentById(id).getData();
        var p = resp;
        PaymentRequestDto form = new PaymentRequestDto(
                p.id(),
                new BookingRequestDto(p.booking().id(), p.booking().trip().id(), p.booking().seatNumber(), p.booking().status()),
                p.customer() != null ? p.customer().id() : null,
                p.amount(),
                p.paymentDate(),
                p.paymentStatus()
        );
        model.addAttribute("payment", form);
        model.addAttribute("mode", "edit");
        return "add-edit-payment";
    }

    @PostMapping("/save")
    public String savePayment(
            @RequestParam(required = false) Integer id,
            @RequestParam Integer customerId,
            @RequestParam BigDecimal amount,
            @RequestParam String paymentDate,
            @RequestParam String paymentStatus,
            @RequestParam String mode,
            @RequestParam("booking.id") Integer bookingId,
            @RequestParam("booking.tripId") Integer tripId,
            @RequestParam("booking.seatNumber") Integer seatNumber,
            @RequestParam("booking.status") String bookingStatus
    ) {
        Instant parsedDate = LocalDateTime
                .parse(paymentDate)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        BookingRequestDto bookingDto = new BookingRequestDto(
                bookingId,
                tripId,
                seatNumber,
                bookingStatus
        );

        PaymentRequestDto newDto = new PaymentRequestDto(
                id,
                bookingDto,
                customerId,
                amount,
                parsedDate,
                paymentStatus
        );

        if ("add".equals(mode)) apiClient.createPayment(newDto);
        else apiClient.updatePayment(newDto.id(), newDto);

        return "redirect:/payments";
    }

}
