package taskmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import taskmanagement.dto.ChangeAssigneeRequestDto;
import taskmanagement.entity.Task;
import taskmanagement.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    TaskService taskService;

    public TaskController(@Autowired TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(@RequestParam(required = false) String author){
        return taskService.getAllTasks(author);
    }

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task, Authentication authentication){
        return taskService.saveTask(task, authentication.getName());
    }

    @PutMapping
    public Task updateTask(@RequestBody ChangeAssigneeRequestDto assignee, @RequestParam Integer id, Authentication authentication){
        return taskService.updateTask(id, assignee.assignee(), authentication.getName());
    }
}
