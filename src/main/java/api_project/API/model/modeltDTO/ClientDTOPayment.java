package api_project.API.model.modeltDTO;

import api_project.API.model.ClientModel;

import java.util.UUID;

public class ClientDTOPayment {
    private UUID id;

    public ClientDTOPayment(){}
    public ClientDTOPayment(ClientModel clientModel) {
        this.id = clientModel.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
