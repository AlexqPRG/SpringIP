package api_project.API.repository;

import api_project.API.model.AutoModel;
import api_project.API.model.DogovorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DogovorRepository extends JpaRepository<DogovorModel, UUID> {
}
