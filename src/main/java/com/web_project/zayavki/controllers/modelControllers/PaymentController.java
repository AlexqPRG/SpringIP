package com.web_project.zayavki.controllers.modelControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web_project.zayavki.models.ClientModel;
import com.web_project.zayavki.models.KindPaymentModel;
import com.web_project.zayavki.models.PaymentModel;
//import com.web_project.zayavki.models.WorkModel;
import com.web_project.zayavki.models.WorkModel;
import com.web_project.zayavki.service.ApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private ApiService apiService;

    private final String url = "/payment";

    @GetMapping("/all")
    public String getPayment(Model model){
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url);

        ArrayList<PaymentModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<PaymentModel>>(){}.getType());

        model.addAttribute("payments",list);

        model.addAttribute("payment", new PaymentModel());

        json = apiService.getDataFromApi("/kindPayment");
        ArrayList<KindPaymentModel> list_kindPayment = new Gson().fromJson(json, new TypeToken<ArrayList<KindPaymentModel>>(){}.getType());

        model.addAttribute("kindPayments", list_kindPayment);

        json = apiService.getDataFromApi("/client");
        ArrayList<ClientModel> list_clients = new Gson().fromJson(json, new TypeToken<ArrayList<ClientModel>>(){}.getType());

        model.addAttribute("clients", list_clients);

//        json = apiService.getDataFromApi("/work");
//        ArrayList<WorkModel> list_works = new Gson().fromJson(json, new TypeToken<ArrayList<WorkModel>>(){}.getType());
//
//        model.addAttribute("works", list_works);

        return "modelPages/paymentPage";
    }

    @GetMapping("/all/{id}")
    public String getPaymentById(@PathVariable("id") UUID id, Model model) throws JsonProcessingException {
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url + "/" +  id);
        PaymentModel payment = new ObjectMapper().readValue(json, PaymentModel.class);
        model.addAttribute("payments", payment);
        model.addAttribute("payment", new PaymentModel());
        return "modelPages/paymentPage";
    }

    @PostMapping("/add")
    public String createPayment(@Valid @ModelAttribute("payment") PaymentModel paymentModel, BindingResult result, Model model){
        model.addAttribute("userRole", getRole());
        if(result.hasErrors()){
            String json = apiService.getDataFromApi(url);
            ArrayList<PaymentModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<PaymentModel>>(){}.getType());
            model.addAttribute("payments", list);
            return "modelPages/paymentPage";
        }
//        paymentModel.setWork(new WorkModel());
        String json = apiService.setDataToApi(url, paymentModel);
        return "redirect:/payment/all";
    }

    @PostMapping("/update")
    public String updatePayment(@Valid @ModelAttribute("payment") PaymentModel paymentModel, BindingResult result) {
//        clientModel.setOrder(orderList);
//        paymentModel.setWork(new WorkModel());
        String json = apiService.updateDataWithApi(url + "/" +  paymentModel.getId(), paymentModel);
        return "redirect:/payment/all";
    }

    @PostMapping("/delete")
    public String deletePayment(@RequestParam UUID id){
        String json = apiService.deleteDataWithApi(url + "/" +  id);
        return "redirect:/payment/all";
    }

    private String getRole(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = "";

        if (authentication != null && authentication.isAuthenticated()) {
            // Получаем роли пользователя
            userRole = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse(null);
        }

        return userRole;
    }
}
