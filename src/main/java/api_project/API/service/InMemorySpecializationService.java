package api_project.API.service;

import api_project.API.model.SpecializationModel;
import api_project.API.repository.SpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemorySpecializationService extends InMemoryAbstractService<SpecializationModel, UUID, SpecializationRepository> {
    private final SpecializationRepository repository;

    public InMemorySpecializationService(SpecializationRepository jpaRepository, SpecializationRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
}