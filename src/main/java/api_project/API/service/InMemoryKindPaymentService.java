package api_project.API.service;

import api_project.API.model.AutoModel;
import api_project.API.model.KindPaymentModel;
import api_project.API.repository.AutoRepository;
import api_project.API.repository.KindPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemoryKindPaymentService extends InMemoryAbstractService<KindPaymentModel, UUID, KindPaymentRepository> {
    private final KindPaymentRepository repository;

    public InMemoryKindPaymentService(KindPaymentRepository jpaRepository, KindPaymentRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
}