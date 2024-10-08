package api_project.API.service;

import api_project.API.model.AutoModel;
import api_project.API.model.DogovorModel;
import api_project.API.repository.AutoRepository;
import api_project.API.repository.DogovorRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemoryDogovorService extends InMemoryAbstractService<DogovorModel, UUID, DogovorRepository> {
    private final DogovorRepository repository;

    public InMemoryDogovorService(DogovorRepository jpaRepository, DogovorRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
}