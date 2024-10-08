package com.web_project.zayavki.models;

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
}
