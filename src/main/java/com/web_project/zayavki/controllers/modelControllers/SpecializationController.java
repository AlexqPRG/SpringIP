package com.web_project.zayavki.controllers.modelControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web_project.zayavki.models.SpecializationModel;
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
@RequestMapping("/specialization")
public class SpecializationController {
    @Autowired
    private ApiService apiService;

    private final String url = "/specialization";

    @GetMapping("/all")
    public String getSpecialization(Model model){
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url);

        ArrayList<SpecializationModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<SpecializationModel>>(){}.getType());

        model.addAttribute("specializations",list);

        model.addAttribute("specialization", new SpecializationModel());

        return "modelPages/specializationPage";
    }

    @GetMapping("/all/{id}")
    public String getSpecializationById(@PathVariable("id") UUID id, Model model) throws JsonProcessingException {
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url + "/" +  id);
        SpecializationModel specialization = new ObjectMapper().readValue(json, SpecializationModel.class);
        model.addAttribute("specializations", specialization);
        model.addAttribute("specialization", new SpecializationModel());
        return "modelPages/specializationPage";
    }

    @PostMapping("/add")
    public String createSpecialization(@Valid @ModelAttribute("specialization") SpecializationModel specializationModel, BindingResult result, Model model){
        model.addAttribute("userRole", getRole());
        if(specializationModel.getName() != null && specializationModel.getName().length() < 3){
            model.addAttribute("errorMessage", "Имя должно быть не менее 3 символов");
        }
        if(result.hasErrors() || model.containsAttribute("errorMessage")){
            String json = apiService.getDataFromApi(url);
            ArrayList<SpecializationModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<SpecializationModel>>(){}.getType());
            model.addAttribute("specializations", list);
            return "modelPages/specializationPage";
        }
        String json = apiService.setDataToApi(url, specializationModel);
        return "redirect:/specialization/all";
    }

    @PostMapping("/update")
    public String updateSpecialization(@Valid @ModelAttribute("specialization") SpecializationModel specializationModel, BindingResult result) {
//        clientModel.setOrder(orderList);
        String json = apiService.updateDataWithApi(url + "/" +  specializationModel.getId(), specializationModel);
        return "redirect:/specialization/all";
    }

    @PostMapping("/delete")
    public String deleteSpecialization(@RequestParam UUID id){
        String json = apiService.deleteDataWithApi(url + "/" +  id);
        return "redirect:/specialization/all";
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
