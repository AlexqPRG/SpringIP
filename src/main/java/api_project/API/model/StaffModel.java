package api_project.API.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class StaffModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String secondName; //фамилия сотрудника
    private String firstName; //имя сотрудниа
    private String patronymic; //отчество сотрудника
    private String numberPhone; //номер телефона сотрудника



    //связь с паспортом
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private PassportModel passport;

    //связь с договором
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<DogovorModel> dogovorModelList;

    //связь с таблицей специализация
    @ManyToMany
    @JoinTable(name = "staff_specialization",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<SpecializationModel> specializationModelList;

    //связь с таблицей работы
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<WorkModel> workModelList;

    //связь с таблицей пользователи
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private UserModel user;

    public StaffModel(){}

    public StaffModel(UUID id, String secondName, String firstName, String patronymic, String numberPhone, PassportModel passport, List<DogovorModel> dogovorModelList, List<SpecializationModel> specializationModelList, List<WorkModel> workModelList, UserModel user) {
        this.id = id;
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.numberPhone = numberPhone;
        this.passport = passport;
        this.dogovorModelList = dogovorModelList;
        this.specializationModelList = specializationModelList;
        this.workModelList = workModelList;
        this.user = user;
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

    public PassportModel getPassport() {
        return passport;
    }

    public void setPassport(PassportModel passport) {
        this.passport = passport;
    }

    public List<DogovorModel> getDogovorModelList() {
        return dogovorModelList;
    }

    public void setDogovorModelList(List<DogovorModel> dogovorModelList) {
        this.dogovorModelList = dogovorModelList;
    }

    public List<SpecializationModel> getSpecializationModelList() {
        return specializationModelList;
    }

    public void setSpecializationModelList(List<SpecializationModel> specializationModelList) {
        this.specializationModelList = specializationModelList;
    }

    public List<WorkModel> getWorkModelList() {
        return workModelList;
    }

    public void setWorkModelList(List<WorkModel> workModelList) {
        this.workModelList = workModelList;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
