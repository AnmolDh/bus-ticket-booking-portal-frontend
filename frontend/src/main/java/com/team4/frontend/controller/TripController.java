package com.team4.frontend.controller;

import com.team4.frontend.client.ApiClient;
import com.team4.frontend.dto.form.TripFormDto;
import com.team4.frontend.dto.request.TripRequestDto;
import com.team4.frontend.dto.response.TripResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Controller
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final ApiClient apiClient;


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("trip", new TripFormDto());
        model.addAttribute("mode", "add");
        populateDropdowns(model);
        return "add-edit-trip";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        TripResponseDto trip = apiClient.getTripById(id).getData();

        TripFormDto formDto = new TripFormDto();
        formDto.setId(trip.id());
        formDto.setRouteId(trip.route().id());
        formDto.setBusId(trip.bus().id());
        formDto.setDriver1Id(trip.driver1Driver().id());
        formDto.setDriver2Id(trip.driver2Driver().id());
        formDto.setBoardingAddressId(trip.boardingAddressId());
        formDto.setDroppingAddressId(trip.droppingAddressId());
        formDto.setDepartureTime(LocalDateTime.ofInstant(trip.departureTime(), ZoneId.systemDefault()));
        formDto.setArrivalTime(LocalDateTime.ofInstant(trip.arrivalTime(), ZoneId.systemDefault()));
        formDto.setAvailableSeats(trip.availableSeats());
        formDto.setFare(trip.fare());
        formDto.setTripDate(LocalDate.ofInstant(trip.tripDate(), ZoneId.systemDefault()));

        model.addAttribute("trip", formDto);
        model.addAttribute("mode", "edit");
        populateDropdowns(model);
        return "add-edit-trip";
    }


    @PostMapping("/save")
    public String saveTrip(@ModelAttribute TripFormDto formDto, @RequestParam String mode) {
        TripRequestDto dto = new TripRequestDto(
                formDto.getId(),
                formDto.getRouteId(),
                formDto.getBusId(),
                formDto.getDriver1Id(),
                formDto.getDriver2Id(),
                formDto.getBoardingAddressId(),
                formDto.getDroppingAddressId(),
                formDto.getDepartureTime() != null ? formDto.getDepartureTime().atZone(ZoneId.systemDefault()).toInstant() : null,
                formDto.getArrivalTime() != null ? formDto.getArrivalTime().atZone(ZoneId.systemDefault()).toInstant() : null,
                formDto.getAvailableSeats(),
                formDto.getFare(),
                formDto.getTripDate() != null ? formDto.getTripDate().atStartOfDay(ZoneId.systemDefault()).toInstant() : null
        );
        log.debug(dto.toString());

        if ("add".equals(mode)) apiClient.createTrip(dto);
        else apiClient.updateTrip(dto.id(), dto);

        return "redirect:/trips";
    }


    private void populateDropdowns(Model model) {
        model.addAttribute("routes", apiClient.getAllRoutes(0, 100).getData().getContent());
        model.addAttribute("buses", apiClient.getAllBuses(0, 100).getData().getContent());
        model.addAttribute("drivers", apiClient.getAllDrivers(0, 100).getData().getContent());
//        model.addAttribute("addresses", apiClient.getAllAddresses(0, 100).getData().getContent());
    }
}
