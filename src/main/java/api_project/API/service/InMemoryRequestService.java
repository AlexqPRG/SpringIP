package api_project.API.service;

import api_project.API.model.PaymentModel;
import api_project.API.model.RequestModel;
import api_project.API.repository.PaymentRepository;
import api_project.API.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemoryRequestService extends InMemoryAbstractService<RequestModel, UUID, RequestRepository> {
    private final RequestRepository repository;

    public InMemoryRequestService(RequestRepository jpaRepository, RequestRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
}