package com.web_project.zayavki.controllers.modelControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web_project.zayavki.models.DogovorModel;
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
@RequestMapping("/dogovor")
public class DogovorController {
    @Autowired
    private ApiService apiService;

    private final String url = "/dogovor";

    @GetMapping("/all")
    public String getDogovor(Model model){
        String json = apiService.getDataFromApi(url);

        ArrayList<DogovorModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<DogovorModel>>(){}.getType());

        model.addAttribute("dogovors",list);

        model.addAttribute("dogovor", new DogovorModel());


        System.out.println(list.size());
        return "modelPages/dogovorPage";
    }

    @GetMapping("/all/{id}")
    public String getDogovorById(@PathVariable("id") UUID id, Model model) throws JsonProcessingException {
        String json = apiService.getDataFromApi(url + "/" +  id);
        DogovorModel dogovor = new ObjectMapper().readValue(json, DogovorModel.class);
        model.addAttribute("dogovors", dogovor);
        model.addAttribute("dogovor", new DogovorModel());
        return "modelPages/dogovorPage";
    }

    @PostMapping("/add")
    public String createDogovor(@Valid @ModelAttribute("dogovor") DogovorModel dogovorModel, BindingResult result, Model model){
        if(result.hasErrors()){
            String json = apiService.getDataFromApi(url);
            ArrayList<DogovorModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<DogovorModel>>(){}.getType());
            model.addAttribute("dogovors", list);
            return "modelPages/dogovorPage";
        }
        String json = apiService.setDataToApi(url, dogovorModel);
        return "redirect:/dogovor/all";
    }

    @PostMapping("/update")
    public String updateDogovor(@Valid @ModelAttribute("dogovor") DogovorModel dogovorModel, BindingResult result) {
        System.out.println(dogovorModel.isUrgent());
//        clientModel.setOrder(orderList);
        String json = apiService.updateDataWithApi(url + "/" +  dogovorModel.getId(), dogovorModel);
        return "redirect:/dogovor/all";
    }

    @PostMapping("/delete")
    public String deleteDogovor(@RequestParam UUID id){
        String json = apiService.deleteDataWithApi(url + "/" +  id);
        return "redirect:/dogovor/all";
    }
}
