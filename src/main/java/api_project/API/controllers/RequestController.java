package api_project.API.controllers;

import api_project.API.model.ClientModel;
import api_project.API.model.RequestModel;
import api_project.API.model.modeltDTO.ClientDTO;
import api_project.API.model.modeltDTO.RequestDTO;
import api_project.API.service.InMemoryRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/request")
public class RequestController {
    @Autowired
    private final InMemoryRequestService requestService;

    public RequestController(InMemoryRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public List<RequestDTO> getAllRequest(){
        List<RequestModel> requestModels = requestService.findAll();
        return requestModels.stream().map(RequestDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RequestModel getRequestById(@PathVariable UUID id){
        return requestService.findById(id);
    }

    @PostMapping
    public RequestModel createRequest(@RequestBody RequestModel request){
        return requestService.createNote(request);
    }

    @PutMapping("/{id}")
    public RequestModel updateRequest(@PathVariable UUID id, @RequestBody RequestModel request){
        request.setId(id);
        return requestService.updateNote(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable UUID id){
        requestService.deleteNote(id);
    }
}
