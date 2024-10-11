package api_project.API.controllers;

import api_project.API.model.SpecializationModel;
import api_project.API.model.StaffModel;
import api_project.API.model.UserModel;
import api_project.API.model.modeltDTO.SpecializationDTO;
import api_project.API.model.modeltDTO.StaffDTO;
import api_project.API.model.modeltDTO.UserDTO;
import api_project.API.service.InMemorySpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/specialization")
public class SpecializationController {
    @Autowired
    private final InMemorySpecializationService specializationService;

    public SpecializationController(InMemorySpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @GetMapping
    public List<SpecializationDTO> getAllSpecialization(){
        List<SpecializationModel> specializationModels = specializationService.findAll();
        return specializationModels.stream().map(SpecializationDTO::new).collect(Collectors.toList());
//        return specializationService.findAll();
    }

    @GetMapping("/{id}")
    public SpecializationDTO getSpecializationById(@PathVariable UUID id){
        SpecializationDTO specializationDTO = new SpecializationDTO(specializationService.findById(id));
        return specializationDTO;
//        return specializationService.findById(id);
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
