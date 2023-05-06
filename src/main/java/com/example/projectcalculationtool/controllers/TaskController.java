package com.example.projectcalculationtool.controllers;

import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String createTask(Model model) { //TODO needs an subprojectID or Session
        Task task = new Task();
        model.addAttribute("task", task);
        return "createTaskForm";
    }

    @PostMapping()
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
}
