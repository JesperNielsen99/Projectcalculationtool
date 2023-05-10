package com.example.projectcalculationtool.controllers;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.services.SubprojectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path= "/project")
public class SubprojectController {
    private SubprojectService subprojectService;

    public SubprojectController(SubprojectService subprojectService) {
        this.subprojectService = subprojectService;
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping("/subprojects")
    public String showSubprojects(Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            Project project = (Project) session.getAttribute("project");
            List<Subproject> subprojects = subprojectService.getSubprojects(project.getProjectID());
            model.addAttribute("subprojects", subprojects);
            return "subprojects";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Create project ----------------------------------------- */

    @GetMapping("/subproject/create")
    public String createSubproject(Model model, HttpSession session){
        if (isLoggedIn(session)) {
            Subproject subproject = new Subproject();
            model.addAttribute("subproject", subproject);
            return "createSubprojectForm";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("/subproject/create")
    public String createSubproject(@ModelAttribute Subproject subproject){
        subprojectService.createSubproject(subproject);
        return "redirect:/create";
    }

    /* ------------------------------------ Update project ----------------------------------------- */

    @GetMapping("/subproject/update")
    public String updateProjectForm(@RequestParam int subprojectID, Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            Subproject subproject = subprojectService.getSubproject(subprojectID);
            model.addAttribute("subproject", subproject);
            return "updateSubprojectForm";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("/subproject/update")
    public String updateProjectSubmit(@ModelAttribute Subproject subproject, HttpSession session) {
        if (isLoggedIn(session)) {
            subprojectService.updateSubproject(subproject);
            return "redirect:/subprojects";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Delete subproject ----------------------------------------- */

    @GetMapping("/subproject/delete")
    public String deleteProject(@RequestParam int subprojectID, HttpSession session){
        if (isLoggedIn(session)) {
            subprojectService.deleteSubproject(subprojectID);
            return "redirect:/subprojects";
        }
        return "redirect:/sign-in";
    }

}
