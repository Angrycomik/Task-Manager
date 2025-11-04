package taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taskmanagement.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findAllByAuthorOrderByIdDesc(String author);
    List<Task> findAllByOrderByIdDesc();
}

