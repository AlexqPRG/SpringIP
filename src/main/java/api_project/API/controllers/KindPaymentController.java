package api_project.API.controllers;

import api_project.API.model.KindPaymentModel;
import api_project.API.model.StatusModel;
import api_project.API.model.modeltDTO.ClientDTO;
import api_project.API.model.modeltDTO.KindPaymentDTO;
import api_project.API.model.modeltDTO.StatusDTO;
import api_project.API.service.InMemoryKindPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/kindPayment")
public class KindPaymentController {
    @Autowired
    private final InMemoryKindPaymentService kindPaymentService;

    public KindPaymentController(InMemoryKindPaymentService kindPaymentService) {
        this.kindPaymentService = kindPaymentService;
    }

    @GetMapping
    public List<KindPaymentDTO> getAllKindPayment(){
        List<KindPaymentModel> kindPaymentModels = kindPaymentService.findAll();
        return kindPaymentModels.stream().map(KindPaymentDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public KindPaymentDTO getKindPaymentById(@PathVariable UUID id){
        KindPaymentDTO kindPaymentDTO = new KindPaymentDTO(kindPaymentService.findById(id));
        return kindPaymentDTO;
    }

    @PostMapping
    public KindPaymentModel createKindPayment(@RequestBody KindPaymentModel kindPayment){
        return kindPaymentService.createNote(kindPayment);
    }

    @PutMapping("/{id}")
    public KindPaymentModel updateKindPayment(@PathVariable UUID id, @RequestBody KindPaymentModel kindPayment){
        kindPayment.setId(id);
        return kindPaymentService.updateNote(kindPayment, id);
    }

    @DeleteMapping("/{id}")
    public void deleteKindPayment(@PathVariable UUID id){
        kindPaymentService.deleteNote(id);
    }
}
