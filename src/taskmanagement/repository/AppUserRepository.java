package taskmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import taskmanagement.entity.AppUser;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);
}

