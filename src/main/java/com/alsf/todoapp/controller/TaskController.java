package com.alsf.todoapp.controller;

import com.alsf.todoapp.dto.TaskDTO;
import com.alsf.todoapp.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    // ajouter une tache
    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO){
        return taskService.createTask(taskDTO);
    }
//récupérer la liste des  taches
    @GetMapping
    public List<TaskDTO> getAllTasks(){
        return taskService.getAllTasks();
    }
// récupérer une tache grace à son id
    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }
    // modifier une tache
    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id,
                              @RequestBody TaskDTO taskDTO){
        return taskService.updateTask(id, taskDTO);
    }
    // supprimer une tache
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}