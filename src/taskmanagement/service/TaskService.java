package taskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskmanagement.entity.Task;
import taskmanagement.repository.TaskRepository;

import java.util.List;


@Service
public class TaskService {
    TaskRepository taskRepository;
    public TaskService(@Autowired TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task saveTask(Task task, String username){
        task.setAuthor(username);
        task.setStatus("CREATED");
        return taskRepository.save(task);

    }

    public List<Task> getAllTasks(String author) {
        return author == null ? taskRepository.findAllByOrderByIdDesc() : taskRepository.findAllByAuthorOrderByIdDesc(author.toLowerCase());
    }
}
