package api_project.API.controllers;

import api_project.API.model.DogovorModel;
import api_project.API.model.modeltDTO.DogovorDTO;
import api_project.API.service.InMemoryDogovorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/dogovor")
public class DogovorController {
    @Autowired
    private final InMemoryDogovorService dogovorService;

    public DogovorController(InMemoryDogovorService dogovorService) {
        this.dogovorService = dogovorService;
    }

    @GetMapping
    public List<DogovorDTO> getAllDogovor(){
        List<DogovorModel> dogovorModels = dogovorService.findAll();
        return dogovorModels.stream().map(DogovorDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DogovorDTO getDogovorById(@PathVariable UUID id){
        DogovorDTO dogovorDTO = new DogovorDTO(dogovorService.findById(id));
        return dogovorDTO;
    }

    @PostMapping
    public DogovorModel createDogovor(@RequestBody DogovorModel dogovor){
        return dogovorService.createNote(dogovor);
    }

    @PutMapping("/{id}")
    public DogovorModel updateDogovor(@PathVariable UUID id, @RequestBody DogovorModel dogovor){
        dogovor.setId(id);
        return dogovorService.updateNote(dogovor, id);
    }

    @DeleteMapping("/{id}")
    public void deleteDogovor(@PathVariable UUID id){
        dogovorService.deleteNote(id);
    }
}
