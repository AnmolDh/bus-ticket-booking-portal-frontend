package com.team4.frontend.controller;

import com.team4.frontend.client.ApiClient;
import com.team4.frontend.dto.response.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    private final ApiClient apiClient;

    public ViewController(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @GetMapping("/")
    public String showTiles() {
        return "index";
    }

    @GetMapping("/bookings")
    public String bookings(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           Model model) {
        PagedResponse<BookingResponseDto> response = apiClient.getAllBookings(page, size).getData();
        addPaginationAttributes(model, response);
        return "bookings";
    }

    @GetMapping("/trips")
    public String trips(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        Model model) {
        PagedResponse<TripResponseDto> response = apiClient.getAllTrips(page, size).getData();
        addPaginationAttributes(model, response);
        return "trips";
    }

    @GetMapping("/drivers")
    public String drivers(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          Model model) {
        PagedResponse<DriverResponseDto> response = apiClient.getAllDrivers(page, size).getData();
        addPaginationAttributes(model, response);
        return "drivers";
    }

    @GetMapping("/buses")
    public String buses(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        Model model) {
        PagedResponse<BusResponseDto> response = apiClient.getAllBuses(page, size).getData();
        addPaginationAttributes(model, response);
        return "buses";
    }

    @GetMapping("/offices")
    public String offices(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          Model model) {
        PagedResponse<AgencyOfficeResponseDto> response = apiClient.getAllAgencyOffices(page, size).getData();
        addPaginationAttributes(model, response);
        return "offices";
    }

    @GetMapping("/customers")
    public String customers(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model) {
        PagedResponse<CustomerResponseDto> response = apiClient.getAllCustomers(page, size).getData();
        addPaginationAttributes(model, response);
        return "customers";
    }

    @GetMapping("/payments")
    public String payments(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           Model model) {
        PagedResponse<PaymentResponseDto> response = apiClient.getAllPayments(page, size).getData();
        addPaginationAttributes(model, response);
        return "payments";
    }

    private <T> void addPaginationAttributes(Model model, PagedResponse<T> response) {
        model.addAttribute("items", response.getContent());
        model.addAttribute("currentPage", response.getPage());
        model.addAttribute("pageSize", response.getSize());
        model.addAttribute("totalPages", response.getTotalPages());
        model.addAttribute("totalElements", response.getTotalElements());
    }
}
