package api_project.API.controllers;

import api_project.API.model.SpecializationModel;
import api_project.API.model.StaffModel;
import api_project.API.model.UserModel;
import api_project.API.model.modeltDTO.SpecializationDTO;
import api_project.API.model.modeltDTO.StaffDTO;
import api_project.API.model.modeltDTO.UserDTO;
import api_project.API.repository.SpecializationRepository;
import api_project.API.repository.UserRepository;
import api_project.API.service.InMemoryStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/staff")
public class StaffController {
    @Autowired
    private final InMemoryStaffService staffService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final SpecializationRepository specializationRepository;

    public StaffController(InMemoryStaffService staffService, UserRepository userRepository, SpecializationRepository specializationRepository) {
        this.staffService = staffService;
        this.userRepository = userRepository;
        this.specializationRepository = specializationRepository;
    }

    @GetMapping
    public List<StaffDTO> getAllStaff(){
        List<StaffModel> staffModels = staffService.findAll();
        return staffModels.stream().map(StaffDTO::new).collect(Collectors.toList());
//        return staffService.findAll();
    }

    @GetMapping("/{id}")
    public StaffModel getStaffById(@PathVariable UUID id){
        return staffService.findById(id);
    }

    @PostMapping
    public StaffModel createStaff(@RequestBody StaffModel staff){
//        return staffService.createNote(staff);
        List<UserModel> list = (List<UserModel>) userRepository.findAll();
        UserModel user =  list.stream().filter(userModel -> userModel.getId().equals(staff.getUser().getId())).findFirst().orElseThrow(() -> new IllegalArgumentException("Пользователь не существует"));

//        List<SpecializationModel> specializationModels = specializationRepository.findAll();
//        specializationModels.stream().map(SpecializationDTO::new).collect(Collectors.toList());
//        staff.setSpecializationModelList(specializationModels);

        staff.setUser(user);
        return staffService.createNote(staff);
    }

    @PutMapping("/{id}")
    public StaffModel updateStaff(@PathVariable UUID id, @RequestBody StaffModel staff){
        staff.setId(id);
        return staffService.updateNote(staff, id);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable UUID id){
        staffService.deleteNote(id);
    }
}
