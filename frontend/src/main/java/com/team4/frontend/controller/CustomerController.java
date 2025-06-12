package com.team4.frontend.controller;

import com.team4.frontend.client.ApiClient;
import com.team4.frontend.dto.request.CustomerRequestDto;
import com.team4.frontend.dto.response.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ApiClient apiClient;

    @GetMapping("/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new CustomerRequestDto(null, "", "", "", null));
        return "add-edit-customer";
    }

    @GetMapping("/edit/{id}")
    public String showEditCustomerForm(@PathVariable Integer id, Model model) {
        CustomerResponseDto customer = apiClient.getCustomerById(id).getData();
        CustomerRequestDto dto = new CustomerRequestDto(
                customer.id(),
                customer.name(),
                customer.email(),
                customer.phone(),
                customer.address() != null ? customer.address().id() : null
        );
        model.addAttribute("customer", dto);
        return "add-edit-customer";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute CustomerRequestDto customer,
                                 @RequestParam String mode) {
        if ("add".equals(mode)) {
            apiClient.createCustomer(customer);
        } else {
            apiClient.updateCustomer(customer.id(), customer);
        }
        return "redirect:/customers";
    }
}
