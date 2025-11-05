package taskmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import taskmanagement.dto.ChangeAssigneeRequestDto;
import taskmanagement.dto.ChangeStatusRequestDto;
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
    public List<Task> getAllTasks(@RequestParam(required = false) String author,@RequestParam(required = false) String assignee){
        return taskService.getAllTasks(author, assignee);
    }

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task, Authentication authentication){
        return taskService.saveTask(task, authentication.getName());
    }

    @PutMapping("/{taskId}/assign")
    public Task updateTaskAssignee(@Valid @RequestBody ChangeAssigneeRequestDto assignee, @PathVariable Integer taskId, Authentication authentication){
        return taskService.updateTaskAssignee(taskId, assignee.getAssignee(), authentication.getName());
    }

    @PutMapping("/{taskId}/status")
    public Task updateTaskStatus(@Valid @RequestBody ChangeStatusRequestDto status, @PathVariable Integer taskId, Authentication authentication){
        return taskService.updateTaskStatus(taskId, status.getStatus(), authentication.getName());
    }
}
