package com.team4.frontend.controller;

import com.team4.frontend.client.ApiClient;
import com.team4.frontend.dto.request.DriverRequestDto;
import com.team4.frontend.dto.response.DriverResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final ApiClient apiClient;

    // Show Add Form
    @GetMapping("/add")
    public String showAddDriverForm(Model model) {
        model.addAttribute("driver", new DriverRequestDto(null, "", "", "", null, null));
        model.addAttribute("mode", "add");
        return "add-edit-driver";
    }

    // Show Edit Form
    @GetMapping("/edit/{id}")
    public String showEditDriverForm(@PathVariable Integer id, Model model) {
        DriverResponseDto d = apiClient.getDriverById(id).getData();
        model.addAttribute("driver", new DriverRequestDto(
                d.id(), d.licenseNumber(), d.name(), d.phone(), d.office().id(), d.address().id()
        ));
        model.addAttribute("mode", "edit");
        return "add-edit-driver";
    }

    // Handle Add or Update
    @PostMapping("/save")
    public String saveDriver(@ModelAttribute DriverRequestDto driver,
                             @RequestParam String mode) {
        if ("add".equals(mode)) {
            apiClient.createDriver(driver);
        } else {
            apiClient.updateDriver(driver.id(), driver);
        }
        return "redirect:/drivers";
    }

}
