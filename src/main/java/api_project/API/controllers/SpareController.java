package api_project.API.controllers;

//import api_project.API.model.SpareModel;
import api_project.API.model.SpareModel;
import api_project.API.service.InMemorySpareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/spare")
public class SpareController {
    @Autowired
    private final InMemorySpareService spareService;

    public SpareController(InMemorySpareService spareService) {
        this.spareService = spareService;
    }

    @GetMapping
    public List<SpareModel> getAllSpare(){
        return spareService.findAll();
    }

    @GetMapping("/{id}")
    public SpareModel getSpareById(@PathVariable UUID id){
        return spareService.findById(id);
    }

    @PostMapping
    public SpareModel createSpare(@RequestBody SpareModel spare){
        return spareService.createNote(spare);
    }

    @PutMapping("/{id}")
    public SpareModel updateSpare(@PathVariable UUID id, @RequestBody SpareModel spare){
        spare.setId(id);
        return spareService.updateNote(spare, id);
    }

    @DeleteMapping("/{id}")
    public void deleteSpare(@PathVariable UUID id){
        spareService.deleteNote(id);
    }
}
