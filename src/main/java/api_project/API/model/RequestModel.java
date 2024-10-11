package api_project.API.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class RequestModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String description;
    private String date;


    @ManyToOne
    @JoinColumn(name = "status_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private StatusModel status;

    public RequestModel(){}

    public RequestModel(UUID id, String description, String date, StatusModel status) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }
}
