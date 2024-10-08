package com.web_project.zayavki.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.UUID;

@Entity
public class PassportModel {
    @Id
    @GeneratedValue
    private UUID id;
    private int series; //серия
    private int number; //номер
    private String date_of_issue; //дата выдачи
    private String name_of_subdivision; //имя выдавшего органа
    private String code_of_subdivision; //код подразделения

    //связь паспорта с таблицей сотрудники
    @OneToOne(optional = false, mappedBy = "passport")
    private StaffModel owner;
}
