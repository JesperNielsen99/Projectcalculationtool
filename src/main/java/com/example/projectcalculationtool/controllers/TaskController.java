package com.example.projectcalculationtool.controllers;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.services.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path="/project/subproject")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping("/tasks")
    public String showTasks(Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            session.removeAttribute("task");
            Subproject subproject = (Subproject) session.getAttribute("subproject");
            List<Task> tasks = taskService.getTasks(subproject.getSubprojectID());
            model.addAttribute("tasks", tasks);
            return "show-tasks";
        }
        return "redirect:/sign-in";
    }

    @GetMapping("/task")
    public String showTask(@RequestParam int taskID, Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            Task task = taskService.getTask(taskID);
            model.addAttribute("task", task);
            return "readTask";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Create project ----------------------------------------- */

    @GetMapping("task/create")
    public String createTask(Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            Task task = new Task();
            model.addAttribute("task", task);
            return "create-task-form";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("task/create")
    public String addTask(@ModelAttribute Task task, HttpSession session) {
        if (isLoggedIn(session)) {
            Subproject subproject = (Subproject) session.getAttribute("subproject");
            task.setSubprojectID(subproject.getSubprojectID());
            if (task.getDeadline() == null) {
                task.setDeadline(LocalDate.now());
            }
            taskService.createTask(task);
        return "redirect:/project/subproject/updateDuration";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Update project ----------------------------------------- */

    @GetMapping("task/update")
    public String updateTask(@RequestParam int taskID, Model model, HttpSession session){
        if (isLoggedIn(session)) {
            Task task = taskService.getTask(taskID);
            session.setAttribute("taskDeadline", task.getDeadline());
            model.addAttribute("task", task);
            return "update-task-form";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("task/update")
    public String updateTask(@ModelAttribute Task task, HttpSession session){
        if (isLoggedIn(session)) {
            Subproject subproject = (Subproject) session.getAttribute("subproject");
            task.setSubprojectID(subproject.getSubprojectID());
            if (task.getDeadline() == null) {
                LocalDate deadline = (LocalDate) session.getAttribute("taskDeadline");
                task.setDeadline(deadline);
            }
            taskService.updateTask(task);
            session.setAttribute("task", task);
            return "redirect:/project/subproject/updateDuration";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("/task/update/completed")
    public String updateCompleted(@RequestParam int taskID, @RequestParam boolean completed, HttpSession session) {
        Task task = taskService.getTask(taskID);
        task.setCompleted(completed);
        if (isLoggedIn(session)) {
            taskService.updateTask(task);
            session.setAttribute("task", task);
            return "redirect:/project/subproject/tasks";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Delete subproject ----------------------------------------- */

    @GetMapping("task/delete")
    public String deleteTask(@RequestParam int taskID, HttpSession session){
        if (isLoggedIn(session)) {
            taskService.deleteTask(taskID);
            return "redirect:/project/subproject/updateDuration";
        }
        return "redirect:/sign-in";
    }
}
