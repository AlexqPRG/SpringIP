package com.web_project.zayavki.controllers.modelControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web_project.zayavki.models.AutoModel;
import com.web_project.zayavki.service.ApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/auto")
public class AutoController {
    @Autowired
    private ApiService apiService;

    private final String url = "/auto";

    @GetMapping("/all")
    public String getAuto(Model model){

        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url);

        ArrayList<AutoModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<AutoModel>>(){}.getType());

        model.addAttribute("autos",list);

        model.addAttribute("auto", new AutoModel());


        return "modelPages/autoPage";
    }

    @GetMapping("/all/{id}")
    public String getAutoById(@PathVariable("id") UUID id, Model model) throws JsonProcessingException {
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url + "/" +  id);
        AutoModel auto = new ObjectMapper().readValue(json, AutoModel.class);
        model.addAttribute("autos", auto);
        model.addAttribute("auto", new AutoModel());
        return "modelPages/autoPage";
    }

    @PostMapping("/add")
    public String createAuto(@Valid @ModelAttribute("auto") AutoModel autoModel, BindingResult result, Model model){
        model.addAttribute("userRole", getRole());
        if (autoModel.getBrand() != null && autoModel.getBrand().length() < 2) {
            model.addAttribute("errorMessage", "Марка не должна быть менее 2 символов");
        }
        if(autoModel.getModel() != null && autoModel.getModel().length() < 5){
            model.addAttribute("errorMessage", "Модель должна быть больше 5 символов");
        }
        if(autoModel.getVin() != null && autoModel.getVin().length() < 7){
            model.addAttribute("errorMessage", "VIN номер должен быть не менее 7 символов");
        }
        if(result.hasErrors() || model.containsAttribute("errorMessage")){
            String json = apiService.getDataFromApi(url);
            ArrayList<AutoModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<AutoModel>>(){}.getType());
            model.addAttribute("autos", list);
            model.addAttribute("auto", new AutoModel());
            return "modelPages/autoPage";
        }
        String json = apiService.setDataToApi(url, autoModel);
        return "redirect:/auto/all";
    }

    @PostMapping("/update")
    public String updateAuto(@Valid @ModelAttribute("auto") AutoModel autoModel, BindingResult result) {
        String json = apiService.updateDataWithApi(url + "/" +  autoModel.getId(), autoModel);
        return "redirect:/auto/all";
    }

    @PostMapping("/delete")
    public String deleteAuto(@RequestParam UUID id){
        String json = apiService.deleteDataWithApi(url + "/" +  id);
        return "redirect:/auto/all";
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
