package com.example.projectcalculationtool.controllers;

import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    public UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model){
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@ModelAttribute User user) {
        service.createUser(user);
        return "redirect:/login";
    }
}
