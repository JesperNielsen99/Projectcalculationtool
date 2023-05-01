package com.example.projectcalculationtool.controllers;


import com.example.projectcalculationtool.services.ProjectService;
import org.springframework.stereotype.Controller;

@Controller
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;

    }
}
