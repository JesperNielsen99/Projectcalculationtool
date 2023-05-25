package com.example.projectcalculationtool.controllers;

import com.example.projectcalculationtool.models.DTO.TaskUserDTO;
import com.example.projectcalculationtool.models.DTO.UserAssignDTO;
import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.services.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getRoleID() == 1) {
            return true;
        }
        return false;
    }

    @GetMapping("tasks")
    public String showTasks(Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            session.removeAttribute("task");
            User user = (User) session.getAttribute("user");

            List<UserAssignDTO> userAssignDTOList = null;// 1.0 Declare variable
            List<TaskUserDTO> tasks = null; // 2.0 Changed to from Task to TaskUserDTO

            if (isAdmin(session)) {
                Subproject subproject = (Subproject) session.getAttribute("subproject");

                tasks = taskService.getTaskUsersDTO(subproject.getSubprojectID()); // 2.1 Changed to from Task to TaskUserDTO

                userAssignDTOList = taskService.getUserAssignDTO(); //1.1 Get list of users / users to assign

            } else {
                //tasks = taskService.getUserTasks(user.getUserID());
            }

            model.addAttribute("isAdmin", isAdmin(session));
            model.addAttribute("roleID", user.getRoleID()) ;
            model.addAttribute("tasks", tasks);
            model.addAttribute("userAssignDTOList", userAssignDTOList); //1.2 Add to model
            model.addAttribute("assignedUsers", new ArrayList<Integer>()); //3.0 Empty list object

            return "show-tasks";
        }
        return "redirect:/sign-in";
    }

    @PostMapping("/task/assign")
    public String assignUser(@ModelAttribute List<Integer> assignedUsers, @RequestParam  int taskID, HttpSession session){
        if(isAdmin(session)) {
            taskService.addAssignedUsersToTask(assignedUsers,taskID);
            return "redirect:/project/subproject/tasks";
        }
        return "redirect:/sign-in";
    }

    /* ------------------------------------ Create project ----------------------------------------- */

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
            if (task.getDeadline() == null) {
                LocalDate deadline = (LocalDate) session.getAttribute("taskDeadline");
                task.setDeadline(deadline);
            }
            session.removeAttribute("taskDeadLine");
            taskService.updateTask(task);
            session.setAttribute("task", task);
            if (isAdmin(session)) {
                return "redirect:/project/subproject/updateDuration";
            }
            return "redirect:/project/subproject/updateDurationByUser?subprojectID=" + task.getSubprojectID();
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
