package taskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskmanagement.entity.Task;
import taskmanagement.exception.ForbiddenException;
import taskmanagement.repository.TaskRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class TaskService {
    TaskRepository taskRepository;
    UserService userService;

    public TaskService(@Autowired TaskRepository taskRepository,  @Autowired UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public Task saveTask(Task task, String username){
        task.setAuthor(username);
        task.setStatus("CREATED");
        task.setAssignee("none");
        return taskRepository.save(task);

    }

    public List<Task> getAllTasks(String author, String assignee) {
        if(author == null && assignee == null){return taskRepository.findAllByOrderByIdDesc();}
        if(assignee == null){return taskRepository.findAllByAuthorOrderByIdDesc(author.toLowerCase());}
        if(author == null){return taskRepository.findAllByAssigneeOrderByIdDesc(assignee.toLowerCase());}
        return taskRepository.findAllByAuthorAndAssigneeOrderByIdDesc(author.toLowerCase(), assignee.toLowerCase());
    }

    public Task updateTaskAssignee(Integer id, String assignee, String username) {
        Task task = getTask(id);

        if(!assignee.equals("none") && !userService.userExists(assignee)){throw new NoSuchElementException("User Not Found");}

        if(task.getAuthor().equals(username)){
            task.setAssignee(assignee);
        }else{throw new ForbiddenException("Unauthorized");}

        taskRepository.save(task);
        return task;
    }

    public Task updateTaskStatus(Integer Id, String status, String username) {
            Task task = getTask(Id);
            if(task.getAuthor().equals(username) || task.getAssignee().equals(username)){
                task.setStatus(status);
            }else{throw new ForbiddenException("Unauthorized");}
            taskRepository.save(task);
            return task;
    }

    public Task getTask(Integer id){
        Optional<Task> taskOptional = taskRepository.getTaskById(id);
        if(taskOptional.isEmpty()){throw new NoSuchElementException("Task Not Found");}
        return taskOptional.get();
    }
}

