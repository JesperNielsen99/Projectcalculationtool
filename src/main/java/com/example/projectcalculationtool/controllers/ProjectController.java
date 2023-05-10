package com.example.projectcalculationtool.controllers;


import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.services.ProjectService;
import jakarta.servlet.http.HttpSession;
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

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }


    @GetMapping("/projects")
    public String showProjects(Model model, HttpSession session){
        if (isLoggedIn(session)) {
            session.removeAttribute("project");
            int ID = 1;
            List<Project> projects = projectService.getProjects(ID);
            model.addAttribute("projectList", projects);
            return "projects";
        }
        return "redirect:/sign-in";
    }

    @GetMapping("/project/addToSession")
    public String showProjects(@RequestParam int projectID, HttpSession session){
        if (isLoggedIn(session)) {
            session.setAttribute("project", projectService.getProject(projectID));
            return "redirect:/project/subprojects";
        }
        return "redirect:/sign-in";
    }


    /* ------------------------------------ Create project ----------------------------------------- */

    @GetMapping ("/project/create")
    public String createProject(Model model, HttpSession session){ //TODO Establish how we get managerID
        if (isLoggedIn(session)) {
            Project project = new Project();
            project.setManagerID(1); //TODO use session to grab managerID
            model.addAttribute("project", project);
            return "createProjectForm"; //TODO Make sure to get managerID trasnfered to form
        }
        return "redirect:/sign-in";
    }

    @PostMapping("/project/create")
    public String addProject(@ModelAttribute Project project, HttpSession session){
        if (isLoggedIn(session)) {
            projectService.createProject(project);
            return "redirect:/projects"; //TODO change redirect to homepage
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Update project ----------------------------------------- */

    @GetMapping("project/update")
    public String updateProjectForm(@RequestParam int projectID, Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            session.setAttribute("project", projectService.getProject(projectID));
            Project project = projectService.getProject(projectID);
            model.addAttribute("project", project);
            return "updateProjectForm";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("/project/update")
    public String updateProjectSubmit(@ModelAttribute Project project, HttpSession session) {
        if (isLoggedIn(session)) {
            projectService.updateProject(project);
            session.setAttribute("project", projectService.getProject(project.getProjectID()));
            return "redirect:/projects"; //TODO needs a 'mainPage' as landing page + an ID
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Delete project ----------------------------------------- */

    @GetMapping("project/delete")
    public String deleteProject(@RequestParam int projectID, HttpSession session){
        if (isLoggedIn(session)) {
            projectService.deleteTask(projectID);
            return "redirect:/projects"; //TODO needs a 'mainPage' as landing page + an ID
        }
        return "redirect:/sign-in";
    }
}
