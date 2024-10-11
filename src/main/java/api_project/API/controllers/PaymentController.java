package api_project.API.controllers;

import api_project.API.model.PaymentModel;
import api_project.API.model.WorkModel;
import api_project.API.model.modeltDTO.PaymentDTO;
import api_project.API.service.InMemoryPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/payment")
public class PaymentController {
    @Autowired
    private final InMemoryPaymentService paymentService;

    public PaymentController(InMemoryPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<PaymentDTO> getAllPayment(){
        List<PaymentModel> paymentModels = paymentService.findAll();
        return paymentModels.stream().map(PaymentDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PaymentDTO getPaymentById(@PathVariable UUID id){
        PaymentDTO paymentDTO = new PaymentDTO(paymentService.findById(id));
        return paymentDTO;
    }

    @PostMapping
    public PaymentModel createPayment(@RequestBody PaymentModel payment){
        return paymentService.createNote(payment);
    }

    @PutMapping("/{id}")
    public PaymentModel updatePayment(@PathVariable UUID id, @RequestBody PaymentModel payment){
        payment.setId(id);
        return paymentService.updateNote(payment, id);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable UUID id){
        paymentService.deleteNote(id);
    }
}
