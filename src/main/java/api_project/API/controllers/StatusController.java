package api_project.API.controllers;

import api_project.API.model.RequestModel;
import api_project.API.model.StatusModel;
import api_project.API.model.modeltDTO.RequestDTO;
import api_project.API.model.modeltDTO.StaffDTO;
import api_project.API.model.modeltDTO.StatusDTO;
import api_project.API.service.InMemoryStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/status")
public class StatusController {
    @Autowired
    private final InMemoryStatusService statusService;

    public StatusController(InMemoryStatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<StatusDTO> getAllStatus(){
        List<StatusModel> statusModels = statusService.findAll();
        return statusModels.stream().map(StatusDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StatusModel getStatusById(@PathVariable UUID id){
        return statusService.findById(id);
    }

    @PostMapping
    public StatusModel createStatus(@RequestBody StatusModel status){
        return statusService.createNote(status);
    }

    @PutMapping("/{id}")
    public StatusModel updateStatus(@PathVariable UUID id, @RequestBody StatusModel status){
        status.setId(id);
        return statusService.updateNote(status, id);
    }

    @DeleteMapping("/{id}")
    public void deleteStatus(@PathVariable UUID id){
        statusService.deleteNote(id);
    }
}
