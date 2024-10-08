package api_project.API.controllers;

import api_project.API.model.AutoModel;
import api_project.API.service.InMemoryAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/auto")
public class AutoController {
    @Autowired
    private final InMemoryAutoService autoService;

    public AutoController(InMemoryAutoService autoService) {
        this.autoService = autoService;
    }

    @GetMapping
    public List<AutoModel> getAllAuto(){
        return autoService.findAll();
    }

    @GetMapping("/{id}")
    public AutoModel getAutoById(@PathVariable UUID id){
        return autoService.findById(id);
    }

    @PostMapping
    public AutoModel createAuto(@RequestBody AutoModel auto){
        return autoService.createNote(auto);
    }

    @PutMapping("/{id}")
    public AutoModel updateAuto(@PathVariable UUID id, @RequestBody AutoModel auto){
        auto.setId(id);
        return autoService.updateNote(auto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuto(@PathVariable UUID id){
        autoService.deleteNote(id);
    }
}
