package api_project.API.repository;

import api_project.API.model.AutoModel;
import api_project.API.model.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StatusRepository extends JpaRepository<StatusModel, UUID> {
}
