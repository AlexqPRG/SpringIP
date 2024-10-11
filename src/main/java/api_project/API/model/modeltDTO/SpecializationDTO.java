package api_project.API.model.modeltDTO;

import api_project.API.model.SpecializationModel;

import java.util.UUID;

public class SpecializationDTO {
    private UUID id;
    private String name;

    public SpecializationDTO(){}

    public SpecializationDTO(SpecializationModel specializationModel){
        this.id = specializationModel.getId();
        this.name = specializationModel.getName();
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
