package api_project.API.repository;

import api_project.API.model.AutoModel;
import api_project.API.model.PassportModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PassportRepository extends JpaRepository<PassportModel, UUID> {
}
