package api_project.API.repository;

import api_project.API.model.AutoModel;
import api_project.API.model.WorkModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkRepository extends JpaRepository<WorkModel, UUID> {
}
