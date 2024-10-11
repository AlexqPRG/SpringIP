package api_project.API.model.modeltDTO;

import api_project.API.model.PassportModel;
import api_project.API.model.StaffModel;

import java.util.UUID;

public class PassportDTO {
    private UUID id;
    private int series;
    private int number;
    private String date_of_issue; //дата выдачи
    private String name_of_subdivision; //имя выдавшего органа
    private String code_of_subdivision; //код подразделения

    public PassportDTO(){}

    public PassportDTO(PassportModel passportModel) {
        this.id = passportModel.getId();
        this.series = passportModel.getSeries();
        this.number = passportModel.getNumber();
        this.date_of_issue = passportModel.getDate_of_issue();
        this.name_of_subdivision = passportModel.getName_of_subdivision();
        this.code_of_subdivision = passportModel.getCode_of_subdivision();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate_of_issue() {
        return date_of_issue;
    }

    public void setDate_of_issue(String date_of_issue) {
        this.date_of_issue = date_of_issue;
    }

    public String getName_of_subdivision() {
        return name_of_subdivision;
    }

    public void setName_of_subdivision(String name_of_subdivision) {
        this.name_of_subdivision = name_of_subdivision;
    }

    public String getCode_of_subdivision() {
        return code_of_subdivision;
    }

    public void setCode_of_subdivision(String code_of_subdivision) {
        this.code_of_subdivision = code_of_subdivision;
    }
}
