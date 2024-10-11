package api_project.API.repository;

import api_project.API.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
    boolean existsByUsername(String username);
}
