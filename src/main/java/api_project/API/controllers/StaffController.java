package api_project.API.controllers;

import api_project.API.model.StaffModel;
import api_project.API.service.InMemoryStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/staff")
public class StaffController {
    @Autowired
    private final InMemoryStaffService staffService;

    public StaffController(InMemoryStaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<StaffModel> getAllStaff(){
        return staffService.findAll();
    }

    @GetMapping("/{id}")
    public StaffModel getStaffById(@PathVariable UUID id){
        return staffService.findById(id);
    }

    @PostMapping
    public StaffModel createStaff(@RequestBody StaffModel staff){
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
