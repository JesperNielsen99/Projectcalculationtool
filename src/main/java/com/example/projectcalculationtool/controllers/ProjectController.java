package com.example.projectcalculationtool.controllers;


import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping ("/createProject")
    public String createProject(Model model){ //TODO Establish how we get managerID

        Project project = new Project();
        //project.setManagerID(1);

        model.addAttribute("project", project);

        return "createProjectForm"; //TODO Make sure to get managerID trasnfered to form
    }


    @PostMapping("/createProject")
    public String addProject(@ModelAttribute Project project){
        projectService.createProject(project);
        return "redirect:/createProject"; //TODO change redirect to homepage

    }




}
