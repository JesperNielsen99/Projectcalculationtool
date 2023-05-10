package com.example.projectcalculationtool.controllers;


import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }


    @GetMapping("/projects")
    public String showProjects(Model model){
        int ID = 1;
        List<Project> projects = projectService.getProjects(ID);
        model.addAttribute("projectList", projects);
        return "projects";
    }


    /* ------------------------------------ Create project ----------------------------------------- */

    @GetMapping ("/project/create")
    public String createProject(Model model){ //TODO Establish how we get managerID

        Project project = new Project();
        project.setManagerID(1); //TODO use session to grab managerID
        model.addAttribute("project", project);

        return "createProjectForm"; //TODO Make sure to get managerID trasnfered to form
    }

    @PostMapping("/project/create")
    public String addProject(@ModelAttribute Project project){
        projectService.createProject(project);
        return "redirect:/projects"; //TODO change redirect to homepage
    }

    /* ------------------------------------ Update project ----------------------------------------- */

    @GetMapping("project/update")
    public String updateProjectForm(@RequestParam int projectID, Model model){
        Project project = projectService.getProject(projectID);
        model.addAttribute("project", project);
        return "updateProjectForm";
    }

    @PostMapping("/project/update")
    public String updateProjectSubmit(@ModelAttribute Project project){
        projectService.updateProject(project);
        return "redirect:/projects"; //TODO needs a 'mainPage' as landing page + an ID
    }

    /* ------------------------------------ Delete project ----------------------------------------- */

    @GetMapping("project/delete")
    public String deleteProject(@RequestParam int projectID){
        projectService.deleteTask(projectID);
        return "redirect:/projects"; //TODO needs a 'mainPage' as landing page + an ID
    }



}
