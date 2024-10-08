package api_project.API.controllers;

import api_project.API.model.KindPaymentModel;
import api_project.API.service.InMemoryKindPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/kindPayment")
public class KindPaymentController {
    @Autowired
    private final InMemoryKindPaymentService kindPaymentService;

    public KindPaymentController(InMemoryKindPaymentService kindPaymentService) {
        this.kindPaymentService = kindPaymentService;
    }

    @GetMapping
    public List<KindPaymentModel> getAllKindPayment(){
        return kindPaymentService.findAll();
    }

    @GetMapping("/{id}")
    public KindPaymentModel getKindPaymentById(@PathVariable UUID id){
        return kindPaymentService.findById(id);
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
