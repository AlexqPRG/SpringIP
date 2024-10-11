package com.web_project.zayavki.controllers.modelControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web_project.zayavki.models.StatusModel;
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
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private ApiService apiService;

    private final String url = "/status";

    @GetMapping("/all")
    public String getStatus(Model model){
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url);

        ArrayList<StatusModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<StatusModel>>(){}.getType());

        model.addAttribute("statuss",list);

        model.addAttribute("status", new StatusModel());


        return "modelPages/statusPage";
    }

    @GetMapping("/all/{id}")
    public String getStatusById(@PathVariable("id") UUID id, Model model) throws JsonProcessingException {
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url + "/" +  id);
        StatusModel status = new ObjectMapper().readValue(json, StatusModel.class);
        model.addAttribute("statuss", status);
        model.addAttribute("status", new StatusModel());
        return "modelPages/statusPage";
    }

    @PostMapping("/add")
    public String createStatus(@Valid @ModelAttribute("status") StatusModel statusModel, BindingResult result, Model model){
        model.addAttribute("userRole", getRole());
        if(statusModel.getName() != null && statusModel.getName().length() < 3){
            model.addAttribute("errorMessage", "Имя должно быть не менее 3 символов");
        }
        if(result.hasErrors() || model.containsAttribute("errorMessage")){
            String json = apiService.getDataFromApi(url);
            ArrayList<StatusModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<StatusModel>>(){}.getType());
            model.addAttribute("statuss", list);
            return "modelPages/statusPage";
        }
        String json = apiService.setDataToApi(url, statusModel);
        return "redirect:/status/all";
    }

    @PostMapping("/update")
    public String updateStatus(@Valid @ModelAttribute("status") StatusModel statusModel, BindingResult result) {
        String json = apiService.updateDataWithApi(url + "/" +  statusModel.getId(), statusModel);
        return "redirect:/status/all";
    }

    @PostMapping("/delete")
    public String deleteStatus(@RequestParam UUID id){
        String json = apiService.deleteDataWithApi(url + "/" +  id);
        return "redirect:/status/all";
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
