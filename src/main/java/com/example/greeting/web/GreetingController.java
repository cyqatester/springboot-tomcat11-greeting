package com.example.greeting.web;

import com.example.greeting.web.form.GreetingForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class GreetingController {

    @GetMapping("/homepage")
    public String home(Model model) {
        model.addAttribute("greetingForm", new GreetingForm());
        return "home";
    }

    @PostMapping("/greet")
    public String greet(
            @Valid @ModelAttribute("greetingForm") GreetingForm form,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "home";
        }

        model.addAttribute("firstName", form.getFirstName().trim());
        model.addAttribute("lastName", form.getLastName().trim());
        return "greeting";
    }

    @PostMapping("/home")
    public String returnHome() {
        // Redirect to GET / so a new, empty form is created.
        return "redirect:/homepage";
    }
}
