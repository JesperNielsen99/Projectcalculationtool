package com.example.projectcalculationtool.controllers;

import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.services.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping("/createTask")
    public String createTask(Model model, HttpSession session) { //TODO needs an subprojectID or Session
        if (isLoggedIn(session)) {
            Task task = new Task();
            model.addAttribute("task", task);
            return "createTaskForm";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("/createTask")
    public String addTask(@ModelAttribute Task task, HttpSession session) {
        if (isLoggedIn(session)) {
        taskService.createTask(task);
        return "redirect:/createTaskFrom"; //TODO needs a 'mainPage' as landing page + an ID
        }
        return "redirect:/sign-in";
    }

    @GetMapping("/updateTask")
    public String updateTask(@RequestParam int taskID, Model model, HttpSession session){
        if (isLoggedIn(session)) {
            Task task = taskService.getTask(taskID);
            model.addAttribute("task", task);
            return "updateTask";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task, HttpSession session){
        if (isLoggedIn(session)) {
            taskService.updateTask(task);
            return "redirect:/projectOverview"; //TODO needs a 'mainPage' as landing page + an ID
        }
        return "redirect:/sign-in";
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam int projectID, HttpSession session){
        if (isLoggedIn(session)) {
            taskService.deleteTask(projectID);
            return "redirect:/projectOverview"; //TODO needs a 'mainPage' as landing page + an ID
        }
        return "redirect:/sign-in";
    }
}
