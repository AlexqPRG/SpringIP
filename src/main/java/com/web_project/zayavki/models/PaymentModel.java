package com.web_project.zayavki.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PaymentModel {
    @Id
    @GeneratedValue
    private UUID id;
    private double sum;
    private String date;


    //связь с таблицей виды платежей
    @ManyToOne
    @JoinColumn(name = "KindPayment_id")
    private KindPaymentModel kindPayment;

    //связь с таблицей клиенты
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;

    //связь с таблицей видами работ
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "work_id")
    private WorkModel work;
}
