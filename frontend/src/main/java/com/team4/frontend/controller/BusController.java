package com.team4.frontend.controller;

import com.team4.frontend.client.ApiClient;
import com.team4.frontend.dto.request.BusRequestDto;
import com.team4.frontend.dto.response.BusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/buses")
@RequiredArgsConstructor
public class BusController {

    private final ApiClient apiClient;

    @GetMapping("/add")
    public String showAddBusForm(Model model) {
        model.addAttribute("bus", new BusRequestDto(null, "", 0, "", null));
        model.addAttribute("mode", "add");
        return "add-edit-bus";
    }

    @GetMapping("/edit/{id}")
    public String showEditBusForm(@PathVariable Integer id, Model model) {
        BusResponseDto bus = apiClient.getBusById(id).getData();
        model.addAttribute("bus", new BusRequestDto(
                bus.id(), bus.registrationNumber(), bus.capacity(), bus.type(), bus.office().id()
        ));
        model.addAttribute("mode", "edit");
        return "add-edit-bus";
    }

    @PostMapping("/update")
    public String updateBus(@ModelAttribute BusRequestDto busDto,
                            @RequestParam String mode) {
        if ("add".equals(mode)) {
            apiClient.createBus(busDto);
        } else {
            apiClient.updateBus(busDto.id(), busDto);
        }
        return "redirect:/buses";
    }
}
