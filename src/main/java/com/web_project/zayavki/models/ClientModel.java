package com.web_project.zayavki.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class ClientModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String secondName; //фамилия
    private String firstName; //имя
    private String patronymic; //отчество
    private String numberPhone; //номер телефона


    //связь с таблицей автомобили
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<AutoModel> autoModelList;

    //связь с таблицей пользователи
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserModel user;

    //связь с таблицей платежи
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<PaymentModel> payment;

}
