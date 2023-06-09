package com.example.projectcalculationtool.controllers;


import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.services.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    /* ------------------------------------ Show project ----------------------------------------- */


    @GetMapping("/projects")
    public String showProjects(Model model, HttpSession session){
        if (isLoggedIn(session)) {
            session.removeAttribute("project");
            User user = (User) session.getAttribute("user");
            List<Project> projects = projectService.getProjects(user.getUserID());
            model.addAttribute("projectList", projects);
            return "show-projects";
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
    public String createProjectForm(Model model, HttpSession session){
        if (isLoggedIn(session)) {
            Project project = new Project();
            model.addAttribute("project", project);
            return "create-project-form";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("/project/create")
    public String createProjectSubmit(@ModelAttribute Project project, HttpSession session){
        if (isLoggedIn(session)) {
            User user = (User) session.getAttribute("user");
            project.setManagerID(user.getUserID());
            if (project.getDeadline() == null) {
                project.setDeadline(LocalDate.now());
            }
            projectService.createProject(project);
            return "redirect:/projects";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Update project ----------------------------------------- */

    @GetMapping("project/update")
    public String updateProjectForm(@RequestParam int projectID, Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            Project project = projectService.getProject(projectID);
            session.setAttribute("projectDeadline", project.getDeadline());
            model.addAttribute("project", project);
            return "update-project-form";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("/project/update")
    public String updateProjectSubmit(@ModelAttribute Project project, HttpSession session) {
        if (isLoggedIn(session)) {
            if (project.getDeadline() == null) {
                LocalDate deadline = (LocalDate) session.getAttribute("projectDeadline");
                project.setDeadline(deadline);
            }
            projectService.updateProject(project);
            session.setAttribute("project", project);
            return "redirect:/projects";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("/project/update/completed")
    public String updateCompleted(@RequestParam int projectID, @RequestParam boolean completed, HttpSession session) {
        Project project = projectService.getProject(projectID);
        project.setCompleted(completed);
        if (isLoggedIn(session)) {
            projectService.updateProject(project);
            session.setAttribute("project", project);
            return "redirect:/projects";
        }
        return "redirect:/sign-in";
    }

    @GetMapping("/project/update/duration")
    public String updateProjectDurationFromTask(HttpSession session){
        if (isLoggedIn(session)) {
            updateProjectDuration(session);
            return "redirect:/project/subproject/tasks";
        }
        return "redirect:/sign-in";
    }

    @GetMapping("/project/update/duration/subproject")
    public String updateProjectDurationFromSubproject(HttpSession session){
        if (isLoggedIn(session)) {
            updateProjectDuration(session);
            return "redirect:/project/subprojects";
        }
        return "redirect:/sign-in";
    }

    @GetMapping("/project/update/duration/user")
    private String updateProjectDurationByUser(@RequestParam int projectID, HttpSession session) {
        if (isLoggedIn(session)) {
            Project project = projectService.getProject(projectID);
            projectService.updateProjectDuration(project.getProjectID());
            project = projectService.getProject(project.getProjectID());
            session.setAttribute("project", project);
            return "redirect:/project/subproject/tasks";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Delete project ----------------------------------------- */

    @GetMapping("project/delete")
    public String deleteProject(@RequestParam int projectID, HttpSession session){
        if (isLoggedIn(session)) {
            projectService.deleteProject(projectID);
            return "redirect:/projects";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Delete project ----------------------------------------- */
    private void updateProjectDuration(HttpSession session) {
        Project project = (Project) session.getAttribute("project");
        projectService.updateProjectDuration(project.getProjectID());
        project = projectService.getProject(project.getProjectID());
        session.setAttribute("project", project);
    }
}
