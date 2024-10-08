package api_project.API.service;

import api_project.API.model.AutoModel;
import api_project.API.repository.AutoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class InMemoryAutoService extends InMemoryAbstractService<AutoModel, UUID, AutoRepository> {
    private final AutoRepository repository;

    public InMemoryAutoService(AutoRepository jpaRepository, AutoRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
}