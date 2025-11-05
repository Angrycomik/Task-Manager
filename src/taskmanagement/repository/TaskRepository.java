package taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taskmanagement.entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findAllByAuthorOrderByIdDesc(String author);
    List<Task> findAllByOrderByIdDesc();
    List<Task> findAllByAssigneeOrderByIdDesc(String lowerCase);
    List<Task> findAllByAuthorAndAssigneeOrderByIdDesc(String author,String assignee);

    Optional<Task> getTaskById(Integer id);

}

