package com.web_project.zayavki.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class RequestModel {
    @Id
    @GeneratedValue
    private UUID id;
    //hz
}
