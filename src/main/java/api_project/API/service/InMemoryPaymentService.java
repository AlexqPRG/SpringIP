package api_project.API.service;

import api_project.API.model.PassportModel;
import api_project.API.model.PaymentModel;
import api_project.API.repository.PassportRepository;
import api_project.API.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemoryPaymentService extends InMemoryAbstractService<PaymentModel, UUID, PaymentRepository> {
    private final PaymentRepository repository;

    public InMemoryPaymentService(PaymentRepository jpaRepository, PaymentRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
}