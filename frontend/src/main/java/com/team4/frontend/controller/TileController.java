package com.team4.frontend.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TileController {

    @GetMapping("/")
    public String showTiles() {
        return "index";
    }

    @GetMapping("/option1")
    public String option1(Model model) {
        model.addAttribute("message", "You clicked Option 1");
        return "result";
    }

    @GetMapping("/option2")
    public String option2(Model model) {
        model.addAttribute("message", "You clicked Option 2");
        return "result";
    }

    @GetMapping("/option3")
    public String option3(Model model) {
        model.addAttribute("message", "You clicked Option 3");
        return "result";
    }

    @GetMapping("/option4")
    public String option4(Model model) {
        model.addAttribute("message", "You clicked Option 4");
        return "result";
    }

    @GetMapping("/option5")
    public String option5(Model model) {
        model.addAttribute("message", "You clicked Option 5");
        return "result";
    }

    @GetMapping("/option6")
    public String option6(Model model) {
        model.addAttribute("message", "You clicked Option 6");
        return "result";
    }

    @GetMapping("/option7")
    public String option7(Model model) {
        model.addAttribute("message", "You clicked Option 7");
        return "result";
    }
}
