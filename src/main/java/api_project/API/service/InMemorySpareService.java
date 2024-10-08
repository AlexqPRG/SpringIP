package api_project.API.service;

import api_project.API.model.RequestModel;
import api_project.API.model.SpareModel;
import api_project.API.repository.RequestRepository;
import api_project.API.repository.SpareRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemorySpareService extends InMemoryAbstractService<SpareModel, UUID, SpareRepository> {
    private final SpareRepository repository;

    public InMemorySpareService(SpareRepository jpaRepository, SpareRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
}