package com.example.projectcalculationtool.controllers;

import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/createTask")
    public String createTask(Model model) { //TODO needs an subprojectID or Session
        Task task = new Task();
        model.addAttribute("task", task);
        return "createTaskForm";
    }

    @PostMapping("/createTask")
    public String addTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/createTaskFrom"; //TODO needs a 'mainPage' as landing page + an ID
    }

    @GetMapping("/updateTask")
    public String updateTask(@RequestParam int taskID, Model model){
        Task task = taskService.getTask(taskID);
        model.addAttribute("task", task);
        return "updateTask";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task){
        taskService.updateTask(task);
        return "redirect:/projectOverview"; //TODO needs a 'mainPage' as landing page + an ID
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam int taskID){
        taskService.deleteTask(taskID);
        return "redirect:/projectOverview"; //TODO needs a 'mainPage' as landing page + an ID
    }
}
