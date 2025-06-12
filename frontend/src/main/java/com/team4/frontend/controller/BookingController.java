package com.team4.frontend.controller;

import com.team4.frontend.client.ApiClient;
import com.team4.frontend.dto.request.BookingRequestDto;
import com.team4.frontend.dto.response.BookingResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    private final ApiClient apiClient;

    public BookingController(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @GetMapping("/bookings/edit/{id}")
    public String showEditBookingForm(@PathVariable Integer id, Model model) {
        BookingResponseDto booking = apiClient.getBookingById(id).getData();
        BookingRequestDto form = new BookingRequestDto(
                booking.id(),
                booking.trip().id(),
                booking.seatNumber(),
                booking.status()
        );
        model.addAttribute("booking", form);
        return "add-edit-booking";
    }

    @GetMapping("/bookings/add")
    public String showAddBookingForm(Model model) {
        model.addAttribute("booking", new BookingRequestDto(null, null, null, null));
        return "add-edit-booking";
    }

    @PostMapping("/bookings/update")
    public String updateBooking(
            @ModelAttribute BookingRequestDto bookingRequestDto,
            @RequestParam String mode) {

        if ("add".equals(mode)) {
            apiClient.createBooking(bookingRequestDto);
        } else {
            apiClient.updateBooking(bookingRequestDto.id(), bookingRequestDto);
        }

        return "redirect:/bookings";
    }
}

