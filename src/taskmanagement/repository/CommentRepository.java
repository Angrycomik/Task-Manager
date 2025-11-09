package taskmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import taskmanagement.entity.Comment;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}

