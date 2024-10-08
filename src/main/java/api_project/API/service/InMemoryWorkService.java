package api_project.API.service;

import api_project.API.model.StatusModel;
import api_project.API.model.WorkModel;
import api_project.API.repository.StatusRepository;
import api_project.API.repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemoryWorkService extends InMemoryAbstractService<WorkModel, UUID, WorkRepository> {
    private final WorkRepository repository;

    public InMemoryWorkService(WorkRepository jpaRepository, WorkRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
}