package api_project.API.repository;

import api_project.API.model.AutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutoRepository extends JpaRepository<AutoModel, UUID> {
}
