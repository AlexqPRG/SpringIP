package api_project.API.model.modeltDTO;

import api_project.API.model.StatusModel;

import java.util.UUID;

public class StatusDTO {
    private UUID id;
    private String name;

    public StatusDTO(){}

    public StatusDTO(StatusModel statusModel){
        this.id = statusModel.getId();
        this.name = statusModel.getName();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
