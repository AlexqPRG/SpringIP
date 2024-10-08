package api_project.API.service;

import api_project.API.model.StaffModel;
import api_project.API.model.StatusModel;
import api_project.API.repository.StaffRepository;
import api_project.API.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemoryStatusService extends InMemoryAbstractService<StatusModel, UUID, StatusRepository> {
    private final StatusRepository repository;

    public InMemoryStatusService(StatusRepository jpaRepository, StatusRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
}