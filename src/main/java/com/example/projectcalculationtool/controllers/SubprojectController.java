package com.example.projectcalculationtool.controllers;

import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.services.SubprojectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubprojectController {
    private SubprojectService subprojectService;

    public SubprojectController(SubprojectService subprojectService) {
        this.subprojectService = subprojectService;
    }

    @GetMapping("/createSubproject")
    public String createSubproject(Model model){
        Subproject subproject = new Subproject();
        model.addAttribute("subproject", subproject);
        return "createSubprojectForm";
    }

    @PostMapping("/createSubproject")
    public String createSubproject(@ModelAttribute Subproject subproject){
        subprojectService.createSubproject(subproject);
        return "redirect:/createSubproject"; // TODO: 01-05-2023 change redirect to project
    }
}
