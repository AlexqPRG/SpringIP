package api_project.API.model.modeltDTO;

import api_project.API.model.StaffModel;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class StaffDTODogovor {
    private UUID id;
    private String secondName; //фамилия сотрудника
    private String firstName; //имя сотрудниа
    private String patronymic; //отчество сотрудника
    private String numberPhone; //номер телефона сотрудника

    private PassportDTO passport;
    private List<SpecializationDTO> specializationModelList;

    public StaffDTODogovor(){}

    public StaffDTODogovor(StaffModel staffModel){
        this.id = staffModel.getId();
        this.secondName = staffModel.getSecondName();
        this.firstName = staffModel.getFirstName();
        this.patronymic = staffModel.getPatronymic();
        this.numberPhone = staffModel.getNumberPhone();
        this.passport = new PassportDTO(staffModel.getPassport());
        this.specializationModelList = staffModel.getSpecializationModelList().stream().map(SpecializationDTO::new).collect(Collectors.toList());
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

    public PassportDTO getPassport() {
        return passport;
    }

    public void setPassport(PassportDTO passport) {
        this.passport = passport;
    }

    public List<SpecializationDTO> getSpecializationModelList() {
        return specializationModelList;
    }

    public void setSpecializationModelList(List<SpecializationDTO> specializationModelList) {
        this.specializationModelList = specializationModelList;
    }
}
