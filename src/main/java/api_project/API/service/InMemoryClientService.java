package api_project.API.service;

import api_project.API.model.AutoModel;
import api_project.API.model.ClientModel;
import api_project.API.repository.AutoRepository;
import api_project.API.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemoryClientService extends InMemoryAbstractService<ClientModel, UUID, ClientRepository> {
    private final ClientRepository repository;

    public InMemoryClientService(ClientRepository jpaRepository, ClientRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
}