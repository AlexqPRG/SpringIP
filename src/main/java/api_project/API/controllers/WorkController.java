package api_project.API.controllers;

import api_project.API.model.WorkModel;
import api_project.API.service.InMemoryWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/work")
public class WorkController {
    @Autowired
    private final InMemoryWorkService workService;

    public WorkController(InMemoryWorkService workService) {
        this.workService = workService;
    }

    @GetMapping
    public List<WorkModel> getAllWork(){
        return workService.findAll();
    }

    @GetMapping("/{id}")
    public WorkModel getWorkById(@PathVariable UUID id){
        return workService.findById(id);
    }

    @PostMapping
    public WorkModel createWork(@RequestBody WorkModel work){
        return workService.createNote(work);
    }

    @PutMapping("/{id}")
    public WorkModel updateWork(@PathVariable UUID id, @RequestBody WorkModel work){
        work.setId(id);
        return workService.updateNote(work, id);
    }

    @DeleteMapping("/{id}")
    public void deleteWork(@PathVariable UUID id){
        workService.deleteNote(id);
    }
}
