package com.alsf.todoapp.controller;

import com.alsf.todoapp.dto.TaskDTO;
import com.alsf.todoapp.model.Status;
import com.alsf.todoapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private final TaskService taskService;

    public WebController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String listTasks(Model model) {

        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("task", new TaskDTO());
        model.addAttribute("statuses", Status.values());

        return "tasks";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute TaskDTO taskDTO) {
        taskService.createTask(taskDTO);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/update/{id}")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam("status") Status status) {

        TaskDTO task = taskService.getTaskById(id);
        task.setStatus(status);

        taskService.updateTask(id, task);

        return "redirect:/tasks";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}