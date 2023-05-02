package com.example.projectcalculationtool.controllers;

import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    public UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/signup")
    public String signUpForm(Model model){
        System.out.println("test");
        model.addAttribute("user", new User());
        System.out.println("test1");
        model.addAttribute("roles", service.getRoles());
        System.out.println("test2");
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@ModelAttribute("user") User user) {
        service.createUser(user);
        return "redirect:/login";
    }
}
