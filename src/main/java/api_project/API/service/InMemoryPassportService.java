package api_project.API.service;

import api_project.API.model.DogovorModel;
import api_project.API.model.PassportModel;
import api_project.API.repository.DogovorRepository;
import api_project.API.repository.PassportRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemoryPassportService extends InMemoryAbstractService<PassportModel, UUID, PassportRepository> {
    private final PassportRepository repository;

    public InMemoryPassportService(PassportRepository jpaRepository, PassportRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
}