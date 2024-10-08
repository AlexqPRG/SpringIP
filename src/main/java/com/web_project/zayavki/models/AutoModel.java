package com.web_project.zayavki.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class AutoModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String brand; //марка
    private String model; //модель
    private String vin; //вин номер кузова
    private String dateOfRelease; //дата выпуска


    //связь с таблицей клиенты
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;
}
