package api_project.API.controllers;

import api_project.API.model.ClientModel;
import api_project.API.service.InMemoryClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/client")
public class ClientController {
    @Autowired
    private final InMemoryClientService clientService;

    public ClientController(InMemoryClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientModel> getAllClient(){
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ClientModel getClientById(@PathVariable UUID id){
        return clientService.findById(id);
    }

    @PostMapping
    public ClientModel createClient(@RequestBody ClientModel client){
        return clientService.createNote(client);
    }

    @PutMapping("/{id}")
    public ClientModel updateClient(@PathVariable UUID id, @RequestBody ClientModel client){
        client.setId(id);
        return clientService.updateNote(client, id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable UUID id){
        clientService.deleteNote(id);
    }
}
