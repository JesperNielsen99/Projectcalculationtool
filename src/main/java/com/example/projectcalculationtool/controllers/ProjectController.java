package com.example.projectcalculationtool.controllers;


import com.example.projectcalculationtool.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping ("/create")
    public String createProject(Model model){

        return "";
    }

}
