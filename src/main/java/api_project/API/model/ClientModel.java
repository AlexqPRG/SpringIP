package api_project.API.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class ClientModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String secondName; //фамилия
    private String firstName; //имя
    private String patronymic; //отчество
    private String numberPhone; //номер телефона


    //связь с таблицей автомобили
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<AutoModel> autoModelList;

    //связь с таблицей пользователи
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserModel user;

    //связь с таблицей платежи
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<PaymentModel> payment;

    public ClientModel(){}

    public ClientModel(UUID id, String secondName, String firstName, String patronymic, String numberPhone, List<AutoModel> autoModelList, UserModel user, List<PaymentModel> payment) {
        this.id = id;
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.numberPhone = numberPhone;
        this.autoModelList = autoModelList;
        this.user = user;
        this.payment = payment;
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

    public List<AutoModel> getAutoModelList() {
        return autoModelList;
    }

    public void setAutoModelList(List<AutoModel> autoModelList) {
        this.autoModelList = autoModelList;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<PaymentModel> getPayment() {
        return payment;
    }

    public void setPayment(List<PaymentModel> payment) {
        this.payment = payment;
    }
}
