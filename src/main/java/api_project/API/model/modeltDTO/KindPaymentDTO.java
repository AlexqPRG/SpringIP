package api_project.API.model.modeltDTO;

import api_project.API.model.KindPaymentModel;

import java.util.UUID;

public class KindPaymentDTO {
    private UUID id;
    private String name;

    public KindPaymentDTO(){}

    public KindPaymentDTO(KindPaymentModel kindPaymentModel){
        this.id = kindPaymentModel.getId();
        this.name = kindPaymentModel.getName();
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
