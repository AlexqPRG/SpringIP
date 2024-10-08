package api_project.API.service;

import api_project.API.model.SpecializationModel;
import api_project.API.model.StaffModel;
import api_project.API.repository.SpecializationRepository;
import api_project.API.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemoryStaffService extends InMemoryAbstractService<StaffModel, UUID, StaffRepository> {
    private final StaffRepository repository;

    public InMemoryStaffService(StaffRepository jpaRepository, StaffRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
}