package com.web_project.zayavki.controllers.modelControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web_project.zayavki.models.KindPaymentModel;
import com.web_project.zayavki.service.ApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/kindPayment")
public class KindPaymentController {
    @Autowired
    private ApiService apiService;

    private final String url = "/kindPayment";

    @GetMapping("/all")
    public String getKindPayment(Model model){
        String json = apiService.getDataFromApi(url);

        ArrayList<KindPaymentModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<KindPaymentModel>>(){}.getType());

        model.addAttribute("kindPayments",list);

        model.addAttribute("kindPayment", new KindPaymentModel());

        for(KindPaymentModel fkf: list){
            System.out.println(fkf.getName());
        }

        System.out.println(list.size());
        return "modelPages/kindPaymentPage";
    }

    @GetMapping("/all/{id}")
    public String getKindPaymentById(@PathVariable("id") UUID id, Model model) throws JsonProcessingException {
        String json = apiService.getDataFromApi(url + "/" +  id);
        KindPaymentModel kindPayment = new ObjectMapper().readValue(json, KindPaymentModel.class);
        model.addAttribute("kindPayments", kindPayment);
        model.addAttribute("kindPayment", new KindPaymentModel());
        return "modelPages/kindPaymentPage";
    }

    @PostMapping("/add")
    public String createKindPayment(@Valid @ModelAttribute("kindPayment") KindPaymentModel kindPaymentModel, BindingResult result, Model model){
        if(result.hasErrors()){
            String json = apiService.getDataFromApi(url);
            ArrayList<KindPaymentModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<KindPaymentModel>>(){}.getType());
            model.addAttribute("kindPayments", list);
            return "modelPages/kindPaymentPage";
        }
        String json = apiService.setDataToApi(url, kindPaymentModel);
        return "redirect:/kindPayment/all";
    }

    @PostMapping("/update")
    public String updateKindPayment(@Valid @ModelAttribute("kindPayment") KindPaymentModel kindPaymentModel, BindingResult result) {
//        clientModel.setOrder(orderList);
        String json = apiService.updateDataWithApi(url + "/" +  kindPaymentModel.getId(), kindPaymentModel);
        return "redirect:/kindPayment/all";
    }

    @PostMapping("/delete")
    public String deleteKindPayment(@RequestParam UUID id){
        String json = apiService.deleteDataWithApi(url + "/" +  id);
        return "redirect:/kindPayment/all";
    }
}
