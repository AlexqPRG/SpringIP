package api_project.API.controllers;

import api_project.API.model.ClientModel;
import api_project.API.model.PassportModel;
import api_project.API.model.modeltDTO.ClientDTO;
import api_project.API.model.modeltDTO.PassportDTO;
import api_project.API.service.InMemoryPassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/passport")
public class PassportController {
    @Autowired
    private final InMemoryPassportService passportService;

    public PassportController(InMemoryPassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping
    public List<PassportDTO> getAllPassport(){
        List<PassportModel> passportModels = passportService.findAll();
        return passportModels.stream().map(PassportDTO::new).collect(Collectors.toList());
//        return passportService.findAll();
    }

    @GetMapping("/{id}")
    public PassportModel getPassportById(@PathVariable UUID id){
        return passportService.findById(id);
    }

    @PostMapping
    public PassportModel createPassport(@RequestBody PassportModel passport){
        return passportService.createNote(passport);
    }

    @PutMapping("/{id}")
    public PassportModel updatePassport(@PathVariable UUID id, @RequestBody PassportModel passport){
        passport.setId(id);
        return passportService.updateNote(passport, id);
    }

    @DeleteMapping("/{id}")
    public void deletePassport(@PathVariable UUID id){
        passportService.deleteNote(id);
    }
}
