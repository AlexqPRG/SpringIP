package api_project.API.controllers;

import api_project.API.model.SpecializationModel;
import api_project.API.service.InMemorySpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/specialization")
public class SpecializationController {
    @Autowired
    private final InMemorySpecializationService specializationService;

    public SpecializationController(InMemorySpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @GetMapping
    public List<SpecializationModel> getAllSpecialization(){
        return specializationService.findAll();
    }

    @GetMapping("/{id}")
    public SpecializationModel getSpecializationById(@PathVariable UUID id){
        return specializationService.findById(id);
    }

    @PostMapping
    public SpecializationModel createSpecialization(@RequestBody SpecializationModel specialization){
        return specializationService.createNote(specialization);
    }

    @PutMapping("/{id}")
    public SpecializationModel updateSpecialization(@PathVariable UUID id, @RequestBody SpecializationModel specialization){
        specialization.setId(id);
        return specializationService.updateNote(specialization, id);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecialization(@PathVariable UUID id){
        specializationService.deleteNote(id);
    }
}
