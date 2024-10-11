package com.web_project.zayavki.controllers.modelControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web_project.zayavki.models.SpecializationModel;
import com.web_project.zayavki.models.StaffModel;
import com.web_project.zayavki.models.UserModel;
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
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private ApiService apiService;

    private final String url = "/staff";

    @GetMapping("/all")
    public String getStaff(Model model){
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url);

        ArrayList<StaffModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<StaffModel>>(){}.getType());

        model.addAttribute("staffs",list);

        StaffModel staffModel = new StaffModel();
        staffModel.setSpecializationModelList(new ArrayList<>());
        model.addAttribute("staff", staffModel);

        json = apiService.getDataFromApi("/user");
        ArrayList<UserModel> list_users = new Gson().fromJson(json, new TypeToken<ArrayList<UserModel>>(){}.getType());

        model.addAttribute("users", list_users);

        json = apiService.getDataFromApi("/specialization");
        ArrayList<SpecializationModel> specializationModels = new Gson().fromJson(json, new TypeToken<ArrayList<SpecializationModel>>(){}.getType());

        model.addAttribute("specializations", specializationModels);

        return "modelPages/staffPage";
    }

    @GetMapping("/all/{id}")
    public String getStaffById(@PathVariable("id") UUID id, Model model) throws JsonProcessingException {
        model.addAttribute("userRole", getRole());
        String json = apiService.getDataFromApi(url + "/" +  id);
        StaffModel staff = new ObjectMapper().readValue(json, StaffModel.class);
        model.addAttribute("staffs", staff);
        model.addAttribute("staff", new StaffModel());
        return "modelPages/staffPage";
    }

    @PostMapping("/add")
    public String createStaff(@ModelAttribute("staff") StaffModel staffModel,@RequestParam("specializationIds") List<UUID> listIds, BindingResult result, Model model) throws JsonProcessingException {
        model.addAttribute("userRole", getRole());
        if(staffModel.getFirstName() != null && staffModel.getFirstName().length() < 3){
            model.addAttribute("errorMessage", "Имя должно быть не менее 3 символов");
        }
        if(staffModel.getSecondName() != null && staffModel.getSecondName().length() < 3){
            model.addAttribute("errorMessage", "Фамилия должна быть не менее 3 символов");
        }
        if(staffModel.getPatronymic() != null && staffModel.getPatronymic().length() < 3){
            model.addAttribute("errorMessage", "Отчество должно быть не менее 3 символов");
        }
        if(result.hasErrors() || model.containsAttribute("errorMessage")){
            String json = apiService.getDataFromApi(url);
            ArrayList<StaffModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<StaffModel>>(){}.getType());
            model.addAttribute("staffs", list);
            return "modelPages/staffPage";
        }
        List<SpecializationModel> specializationModels = new ArrayList<>();
        for(var item: listIds){
            String spec_json = apiService.getDataFromApi("/specialization/" + item);
            SpecializationModel specializationModel = new ObjectMapper().readValue(spec_json, SpecializationModel.class);
            specializationModels.add(specializationModel);
        }
        staffModel.setSpecializationModelList(null);
        staffModel.setSpecializationModelList(specializationModels);
        String json = apiService.setDataToApi(url, staffModel);
        return "redirect:/staff/all";
    }

    @PostMapping("/update")
    public String updateStaff(@Valid @ModelAttribute("staff") StaffModel staffModel, BindingResult result) throws JsonProcessingException {
        String json_user = apiService.getDataFromApi("/user/" + staffModel.getUser().getId());
        UserModel user = new ObjectMapper().readValue(json_user, UserModel.class);
        staffModel.setUser(user);
        String json = apiService.updateDataWithApi(url + "/" +  staffModel.getId(), staffModel);
        return "redirect:/staff/all";
    }

    @PostMapping("/delete")
    public String deleteStaff(@RequestParam UUID id){
        String json = apiService.deleteDataWithApi(url + "/" +  id);
        return "redirect:/staff/all";
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
