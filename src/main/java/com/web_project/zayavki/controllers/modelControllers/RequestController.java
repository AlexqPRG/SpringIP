package com.web_project.zayavki.controllers.modelControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web_project.zayavki.models.RequestModel;
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
@RequestMapping("/request")
public class RequestController {
    @Autowired
    private ApiService apiService;

    private final String url = "/request";

    @GetMapping("/all")
    public String getRequest(Model model){
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url);

        ArrayList<RequestModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<RequestModel>>(){}.getType());

        model.addAttribute("requests",list);

        model.addAttribute("request", new RequestModel());

        json = apiService.getDataFromApi("/status");

        ArrayList<StatusModel> list_status = new Gson().fromJson(json, new TypeToken<ArrayList<StatusModel>>(){}.getType());


        model.addAttribute("statuss",list_status);


        return "modelPages/requestPage";
    }

    @GetMapping("/all/{id}")
    public String getRequestById(@PathVariable("id") UUID id, Model model) throws JsonProcessingException {
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url + "/" +  id);
        RequestModel request = new ObjectMapper().readValue(json, RequestModel.class);
        model.addAttribute("requests", request);
        model.addAttribute("request", new RequestModel());
        return "modelPages/requestPage";
    }

    @PostMapping("/add")
    public String createRequest(@Valid @ModelAttribute("request") RequestModel requestModel, BindingResult result, Model model){
        model.addAttribute("userRole", getRole());
        if(requestModel.getDescription() != null && requestModel.getDescription().length() < 10){
            model.addAttribute("errorMessage", "Описание должно быть не менее 10 символов");
        }
        if(result.hasErrors() || model.containsAttribute("errorMessage")){
            String json = apiService.getDataFromApi(url);
            ArrayList<RequestModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<RequestModel>>(){}.getType());
            model.addAttribute("requests", list);
            return "modelPages/requestPage";
        }
        String json = apiService.setDataToApi(url, requestModel);
        return "redirect:/request/all";
    }

    @PostMapping("/update")
    public String updateRequest(@Valid @ModelAttribute("request") RequestModel requestModel, BindingResult result) {
        String json = apiService.updateDataWithApi(url + "/" +  requestModel.getId(), requestModel);
        return "redirect:/request/all";
    }

    @PostMapping("/delete")
    public String deleteRequest(@RequestParam UUID id){
        String json = apiService.deleteDataWithApi(url + "/" +  id);
        return "redirect:/request/all";
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
