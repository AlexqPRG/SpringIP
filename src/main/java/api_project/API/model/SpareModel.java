package api_project.API.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class SpareModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String article;
    private double price;

    public SpareModel(){}

    public SpareModel(UUID id, String article, double price) {
        this.id = id;
        this.article = article;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
