package api_project.API.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class AutoModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String brand; //марка
    private String model; //модель
    private String vin; //вин номер кузова
    private String dateOfRelease; //дата выпуска


    //связь с таблицей клиенты
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;

    public AutoModel(){}

    public AutoModel(UUID id, String brand, String model, String vin, String dateOfRelease, ClientModel client) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.dateOfRelease = dateOfRelease;
        this.client = client;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }
}
