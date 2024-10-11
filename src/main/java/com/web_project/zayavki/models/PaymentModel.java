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

//    //связь с таблицей видами работ
//    @OneToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "work_id")
//    private WorkModel work;

    public PaymentModel(){}

    public PaymentModel(UUID id, double sum, String date, KindPaymentModel kindPayment, ClientModel client) {
        this.id = id;
        this.sum = sum;
        this.date = date;
        this.kindPayment = kindPayment;
        this.client = client;
//        this.work = work;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public KindPaymentModel getKindPayment() {
        return kindPayment;
    }

    public void setKindPayment(KindPaymentModel kindPayment) {
        this.kindPayment = kindPayment;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }
//
//    public WorkModel getWork() {
//        return work;
//    }
//
//    public void setWork(WorkModel work) {
//        this.work = work;
//    }
}
