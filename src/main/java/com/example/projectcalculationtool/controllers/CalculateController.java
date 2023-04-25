package com.example.projectcalculationtool.controllers;

import com.example.projectcalculationtool.services.CalculateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CalculateController {
    private CalculateService service;

    public CalculateController(CalculateService service){
        this.service = service;
    }

    @GetMapping("/index")
    public String getUsers(Model model){
        List<String> userList = service.getUsers();
        model.addAttribute("userList",userList);
        return "index";
    }
}
