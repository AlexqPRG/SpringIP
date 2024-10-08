package com.web_project.zayavki.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class StaffModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String secondName; //фамилия сотрудника
    private String firstName; //имя сотрудниа
    private String patronymic; //отчество сотрудника
    private String numberPhone; //номер телефона сотрудника



    //связь с паспортом
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private PassportModel passport;

    //связь с договором
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<DogovorModel> dogovorModelList;

    //связь с таблицей специализация
    @ManyToMany
    @JoinTable(name = "staff_specialization",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id"))
    private List<SpecializationModel> specializationModelList;

    //связь с таблицей работы
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<WorkModel> workModelList;

    //связь с таблицей пользователи
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserModel user;
}
