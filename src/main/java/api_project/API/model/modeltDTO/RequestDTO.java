package api_project.API.model.modeltDTO;

import api_project.API.model.RequestModel;

import java.util.UUID;

public class RequestDTO {
    private UUID id;
    private String description;
    private String date;
    private StatusDTO status;

    public RequestDTO(){}


    public RequestDTO(RequestModel requestModel) {
        this.id = requestModel.getId();
        this.description = requestModel.getDescription();
        this.date = requestModel.getDate();
        this.status = new StatusDTO(requestModel.getStatus());
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

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }
}
