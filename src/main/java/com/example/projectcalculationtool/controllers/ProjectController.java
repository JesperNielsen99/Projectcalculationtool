package com.example.projectcalculationtool.controllers;


import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.services.ProjectService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }


    @GetMapping("/projectsPage")
    public String showProjects(Model model){
        int ID = 1;
        List<Project> projects = projectService.getProjects(ID);
        model.addAttribute("projectList", projects);
        return "projectPage";
    }


    /* ------------------------------------ Create project ----------------------------------------- */

    @GetMapping ("/projectsPage/createProject")
    public String createProject(Model model){ //TODO Establish how we get managerID

        Project project = new Project();
        project.setManagerID(1); //TODO use session to grab managerID
        model.addAttribute("project", project);

        return "createProjectForm"; //TODO Make sure to get managerID trasnfered to form
    }

    @PostMapping("/projectsPage/createProject")
    public String addProject(@ModelAttribute Project project){
        projectService.createProject(project);
        return "redirect:/projectsPage"; //TODO change redirect to homepage
    }

    @GetMapping("projectsPage/updateProject")
    public String updateProjectForm(@RequestParam int projectID, Model model){
        Project project = projectService.getProject(projectID);
        model.addAttribute("project", project);
        return "updateProjectForm";
    }

    @PostMapping("/projectsPage/updateProject")
    public String updateProjectSubmit(@ModelAttribute Project project){
        projectService.updateProject(project);
        return "redirect:/projectsPage"; //TODO needs a 'mainPage' as landing page + an ID
    }


}
