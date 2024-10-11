package com.web_project.zayavki.controllers.modelControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web_project.zayavki.models.ClientModel;
import com.web_project.zayavki.models.UserModel;
import com.web_project.zayavki.models.modeltDTO.ClientDTO;
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
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ApiService apiService;

    private final String url = "/client";

    @GetMapping("/all")
    public String getClient(Model model){
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url);

        ArrayList<ClientModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<ClientModel>>(){}.getType());

        model.addAttribute("clients",list);

        model.addAttribute("client", new ClientModel());

        json = apiService.getDataFromApi("/user");
        ArrayList<UserModel> list_users = new Gson().fromJson(json, new TypeToken<ArrayList<UserModel>>(){}.getType());

        model.addAttribute("users", list_users);
        return "modelPages/clientPage";
    }

    @GetMapping("/all/{id}")
    public String getClientById(@PathVariable("id") UUID id, Model model) throws JsonProcessingException {
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url + "/" +  id);

        ClientModel client = new ObjectMapper().readValue(json, ClientModel.class);
        model.addAttribute("clients", client);
        model.addAttribute("client", new ClientModel());
        return "modelPages/clientPage";
    }

    @PostMapping("/add")
    public String createClient(@Valid @ModelAttribute("client") ClientModel clientModel, BindingResult result, Model model){
        model.addAttribute("userRole", getRole());
        if(clientModel.getFirstName() != null && clientModel.getFirstName().length() < 3){
            model.addAttribute("errorMessage", "Имя должно быть не менее 3 символов");
        }
        if(clientModel.getSecondName() != null && clientModel.getSecondName().length() < 3){
            model.addAttribute("errorMessage", "Фамилия должна быть не менее 3 символов");
        }
        if(clientModel.getPatronymic() != null && clientModel.getPatronymic().length() < 3){
            model.addAttribute("errorMessage", "Отчество должно быть не менее 3 символов");
        }
        if(clientModel.getNumberPhone() != null && clientModel.getNumberPhone().length() < 3){
            model.addAttribute("errorMessage", "Телефон должен быть не менее 7 символов");
        }
        if(result.hasErrors() || model.containsAttribute("errorMessage")){
            String json = apiService.getDataFromApi(url);
            ArrayList<ClientModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<ClientModel>>(){}.getType());
            model.addAttribute("clients", list);
            return "modelPages/clientPage";
        }
        String json = apiService.setDataToApi(url, clientModel);
        return "redirect:/client/all";
    }

    @PostMapping("/update")
    public String updateClient(@Valid @ModelAttribute("client") ClientModel clientModel, @RequestParam("user_id") UUID user_id, BindingResult result) throws JsonProcessingException {
        String json_user = apiService.getDataFromApi("/user/" + user_id);
        UserModel user = new ObjectMapper().readValue(json_user, UserModel.class);
        clientModel.setUser(user);
        String json = apiService.updateDataWithApi(url + "/" +  clientModel.getId(), clientModel);
        return "redirect:/client/all";
    }

    @PostMapping("/delete")
    public String deleteClient(@RequestParam UUID id){
        String json = apiService.deleteDataWithApi(url + "/" +  id);
        return "redirect:/client/all";
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
