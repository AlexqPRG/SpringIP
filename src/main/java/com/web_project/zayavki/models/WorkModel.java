package com.web_project.zayavki.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class WorkModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String nameOfWork;

    //связь с таблицей сотрудники
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffModel staff;

//    //связь с таблицей платежи
//    @OneToOne(optional = false, mappedBy = "work")
//    private PaymentModel payment;

}
