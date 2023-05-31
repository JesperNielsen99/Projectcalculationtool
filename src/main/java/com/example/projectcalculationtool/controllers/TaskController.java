package com.example.projectcalculationtool.controllers;

import com.example.projectcalculationtool.models.dto.TaskUserDTO;
import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.services.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path = "/project/subproject")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getRoleID() == 1) {
            return true;
        }
        return false;
    }

    /* ------------------------------------ Show task ----------------------------------------- */

    @GetMapping("tasks")
    public String showTasks(Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            session.removeAttribute("task");
            User user = (User) session.getAttribute("user");
            List<TaskUserDTO> taskUserDTOs = null;
            if (isAdmin(session)) {
                Subproject subproject = (Subproject) session.getAttribute("subproject");
                taskUserDTOs = taskService.getTaskUsersDTO(subproject.getSubprojectID());
            } else {
                taskUserDTOs = taskService.getUserTasks(user.getUserID());
            }
            model.addAttribute("taskUserDTOs", taskUserDTOs);
            model.addAttribute("isAdmin", isAdmin(session));
            return "show-tasks";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Create task ----------------------------------------- */

    @GetMapping("task/create")
    public String createTask(Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            Task task = new Task();
            model.addAttribute("isAdmin", isAdmin(session));
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
            User user = (User) session.getAttribute("user");
            String managerName = user.getFirstName() + " " + user.getLastName();
            task.setManagerName(managerName);
            taskService.createTask(task);
            return "redirect:/project/subproject/update/duration";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Update task ----------------------------------------- */

    @GetMapping("task/update")
    public String updateTask(@RequestParam int taskID, Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            Task task = taskService.getTask(taskID);
            session.setAttribute("taskDeadline", task.getDeadline());
            model.addAttribute("task", task);
            return "update-task-form";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("task/update")
    public String updateTask(@ModelAttribute Task task, HttpSession session) {
        if (isLoggedIn(session)) {
            if (task.getDeadline() == null) {
                LocalDate deadline = (LocalDate) session.getAttribute("taskDeadline");
                task.setDeadline(deadline);
            }
            session.removeAttribute("taskDeadLine");
            taskService.updateTask(task);
            session.setAttribute("task", task);
            if (isAdmin(session)) {
                return "redirect:/project/subproject/update/duration";
            }
            return "redirect:/project/subproject/update/duration/user?subprojectID=" + task.getSubprojectID();
        }
        return "redirect:/sign-in";
    }

    @PostMapping("task/update/completed")
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

    /* ------------------------------------ Delete task ----------------------------------------- */

    @GetMapping("task/delete")
    public String deleteTask(@RequestParam int taskID, HttpSession session) {
        if (isLoggedIn(session)) {
            taskService.deleteTask(taskID);
            return "redirect:/project/subproject/updateDuration";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Assign task ----------------------------------------- */

    @PostMapping("task/assign")
    public String assignUser(@RequestParam("usersToAssign") List<Integer> usersToAssign, @RequestParam int taskID, HttpSession session) {
        if (isAdmin(session)) {
            taskService.addAssignedUsersToTask(usersToAssign, taskID);
            return "redirect:/project/subproject/tasks";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("task/unassign")
    public String unassignUser(@RequestParam("usersToUnassign") List<Integer> usersToUnassign, @RequestParam int taskID, HttpSession session) {
        if (isAdmin(session)) {
            taskService.removeAssignedUsersFromTask(usersToUnassign, taskID);
            return "redirect:/project/subproject/tasks";
        }
        return "redirect:/sign-in";
    }
}
