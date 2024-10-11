package api_project.API.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<AutoModel> autos;

    //связь с таблицей пользователи
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private UserModel user;

    //связь с таблицей платежи
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<PaymentModel> payments;

    public ClientModel(){}

    public ClientModel(UUID id, String secondName, String firstName, String patronymic, String numberPhone, List<AutoModel> autos, UserModel user, List<PaymentModel> payments) {
        this.id = id;
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.numberPhone = numberPhone;
        this.autos = autos;
        this.user = user;
        this.payments = payments;
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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<PaymentModel> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentModel> payments) {
        this.payments = payments;
    }
}
