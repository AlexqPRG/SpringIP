package api_project.API.model.modeltDTO;



import api_project.API.model.AutoModel;
import api_project.API.model.ClientModel;
import api_project.API.model.PaymentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ClientDTO {
    private UUID id;
    private String secondName;
    private String firstName;
    private String patronymic;
    private String numberPhone;

    private List<AutoModel> autos;
    private UserDTO user;
    private List<PaymentDTO> payments;

    public ClientDTO(){}

    public ClientDTO(ClientModel clientModel){
        this.id = clientModel.getId();
        this.secondName = clientModel.getSecondName();
        this.firstName = clientModel.getFirstName();
        this.patronymic = clientModel.getPatronymic();
        this.numberPhone = clientModel.getNumberPhone();
        this.autos = clientModel.getAutos();
        this.user = new UserDTO(clientModel.getUser());
        this.payments = clientModel.getPayments().stream()
                    .map(PaymentDTO::new) // Предполагается, что у вас есть конструктор в PaymentDTO, который принимает PaymentModel
                    .collect(Collectors.toList());


    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public List<AutoModel> getAutos() {
        return autos;
    }

    public void setAutos(List<AutoModel> autos) {
        this.autos = autos;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<PaymentDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentDTO> payments) {
        this.payments = payments;
    }
}
