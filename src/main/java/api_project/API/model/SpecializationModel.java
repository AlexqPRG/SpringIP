package api_project.API.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class SpecializationModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    //связь многие ко многим с таблицей сотрудники
    @ManyToMany
    @JoinTable(name = "staff_specialization",
            joinColumns = @JoinColumn(name = "specialization_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id"))
    private List<StaffModel> staffModelList;

    public SpecializationModel(){}

    public SpecializationModel(UUID id, String name, List<StaffModel> staffModelList) {
        this.id = id;
        this.name = name;
        this.staffModelList = staffModelList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StaffModel> getStaffModelList() {
        return staffModelList;
    }

    public void setStaffModelList(List<StaffModel> staffModelList) {
        this.staffModelList = staffModelList;
    }
}
