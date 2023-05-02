package com.example.projectcalculationtool.controllers;

import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    public UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", service.getRoles());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@ModelAttribute("user") User user) {
        service.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        model.addAttribute("role", service.getRole(user.getRoleID()));
        return "profile";
    }

    @GetMapping("/sign-in")
    public String loginForm(HttpSession session) {
        session.invalidate();
        return "sign-in";
    }

    @PostMapping("/sign-in")
    public String loginSubmit(@RequestParam("email") String email, @RequestParam("password") String password,
                              HttpSession session, Model model)
    {
        User user = service.getUser(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(900);
            //return "redirect:/projects";
            return "redirect:/profile";
        }
        // wrong credentials
        model.addAttribute("wrongCredentials", true);
        return "redirect:/sign-in";
    }

    @GetMapping("/sign-out")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/sign-in";
    }
}
