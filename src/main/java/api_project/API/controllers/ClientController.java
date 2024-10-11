package api_project.API.controllers;

import api_project.API.model.ClientModel;
import api_project.API.model.UserModel;
import api_project.API.model.modeltDTO.ClientDTO;
import api_project.API.repository.UserRepository;
import api_project.API.service.InMemoryClientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/client")
public class ClientController {
    @Autowired
    private final InMemoryClientService clientService;

    @Autowired
    private final UserRepository userRepository;

    public ClientController(InMemoryClientService clientService, UserRepository userRepository) {
        this.clientService = clientService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<ClientDTO> getAllClient(){
//        return clientService.findAll();
        List<ClientModel> clientModels = clientService.findAll();
        return clientModels.stream().map(ClientDTO::new).collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable UUID id){
        ClientDTO clientDTO = new ClientDTO(clientService.findById(id));
        System.out.println("ID");
//        return clientService.findById(id);
        return clientDTO;
    }

    @PostMapping
    public ClientModel createClient(@RequestBody ClientModel client){
        List<UserModel> list = (List<UserModel>) userRepository.findAll();
        UserModel user =  list.stream().filter(userModel -> userModel.getId().equals(client.getUser().getId())).findFirst().orElseThrow(() -> new IllegalArgumentException("Пользователь не существует"));

        client.setUser(user);
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
