package com.web_project.zayavki.repository;

import com.web_project.zayavki.models.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
    boolean existsByUsername(String username);
}
