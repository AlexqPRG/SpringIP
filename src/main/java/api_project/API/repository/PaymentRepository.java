package api_project.API.repository;

import api_project.API.model.AutoModel;
import api_project.API.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentModel, UUID> {
}
