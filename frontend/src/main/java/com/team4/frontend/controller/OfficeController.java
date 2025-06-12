package com.team4.frontend.controller;

import com.team4.frontend.client.ApiClient;
import com.team4.frontend.dto.request.AgencyOfficeRequestDto;
import com.team4.frontend.dto.response.AgencyOfficeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/offices")
@RequiredArgsConstructor
public class OfficeController {

    private final ApiClient apiClient;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("office", new AgencyOfficeRequestDto(null, "", "", "", null, null));
        model.addAttribute("mode", "add");
        return "add-edit-office";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        AgencyOfficeResponseDto office = apiClient.getAgencyOfficeById(id).getData();
        AgencyOfficeRequestDto dto = new AgencyOfficeRequestDto(
                office.id(),
                office.officeMail(),
                office.officeContactPersonName(),
                office.officeContactNumber(),
                office.agency() != null ? office.agency().id() : null,
                office.officeAddress() != null ? office.officeAddress().id() : null
        );
        model.addAttribute("office", dto);
        model.addAttribute("mode", "edit");
        return "add-edit-office";
    }

    @PostMapping("/save")
    public String saveOffice(@ModelAttribute("office") AgencyOfficeRequestDto officeDto,
                             @RequestParam String mode) {
        if ("add".equals(mode)) {
            apiClient.createOffice(officeDto);
        } else {
            apiClient.updateOffice(officeDto.id(), officeDto);
        }
        return "redirect:/offices";
    }
}
