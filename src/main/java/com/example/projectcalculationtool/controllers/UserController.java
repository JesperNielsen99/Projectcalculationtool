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
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    /* ------------------------------------ Sign-up user ----------------------------------------- */

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", userService.getRoles());
        return "create-user-form";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Sign-in user ----------------------------------------- */

    @GetMapping(value = {"", "/", "/sign-in"})
    public String loginForm(HttpSession session) {
        session.invalidate();
        return "index-login";
    }

    @PostMapping("/sign-in")
    public String loginSubmit(@RequestParam("email") String email, @RequestParam("password") String password,
                              HttpSession session, Model model) {
        User user = userService.getUser(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(900);
            if (user.getRoleID() == 2) {
                return "redirect:/project/subproject/tasks";
            }
            return "redirect:/projects";
        }
        // wrong credentials
        model.addAttribute("wrongCredentials", true);
        return "index-login";
    }

    /* ------------------------------------ Profile user ----------------------------------------- */

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
            model.addAttribute("role", userService.getRole(user.getRoleID()));
            return "show-user-profile";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Sign-out user ----------------------------------------- */

    @GetMapping("/sign-out")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Delete user ----------------------------------------- */

    @GetMapping("/delete")
    public String deleteUser(HttpSession session) {
        if (isLoggedIn(session)) {
            User user = (User) session.getAttribute("user");
            userService.deleteUser(user.getUserID());
            return "redirect:/sign-in";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Update user ----------------------------------------- */

    @GetMapping("/update")
    public String updateUserForm(Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
            model.addAttribute("roles", userService.getRoles());
            return "update-user-form";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("/update")
    public String updateUserSubmit(@ModelAttribute("user") User user, HttpSession session) {
        if (isLoggedIn(session)) {
            userService.updateUser(user);
            session.setAttribute("user", user);
            return "redirect:/profile";
        }
        return "redirect:/sign-in";
    }
}
