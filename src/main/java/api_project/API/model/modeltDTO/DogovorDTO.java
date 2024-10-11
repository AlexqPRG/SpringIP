package api_project.API.model.modeltDTO;

import api_project.API.model.DogovorModel;

import java.util.UUID;

public class DogovorDTO {
    private UUID id;
    private String functions; //трудовая функция
    private String dateOn; //дата принятия на работу
    private double salary; //оклад
    private String conditions; //условия работы
    private String operation_mode; //режим работы
    private boolean urgent; //срочный?
    private String dateOff; //дата окончания договора (необязательно)
    private StaffDTODogovor staff;

    public DogovorDTO(){}

    public DogovorDTO(DogovorModel dogovorModel){
        this.id = dogovorModel.getId();
        this.functions = dogovorModel.getFunctions();
        this.dateOn = dogovorModel.getDateOn();
        this.salary = dogovorModel.getSalary();
        this.conditions = dogovorModel.getConditions();
        this.operation_mode = dogovorModel.getOperation_mode();
        this.urgent = dogovorModel.isUrgent();
        this.dateOff = dogovorModel.getDateOff();
        this.staff = new StaffDTODogovor(dogovorModel.getStaff());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getDateOn() {
        return dateOn;
    }

    public void setDateOn(String dateOn) {
        this.dateOn = dateOn;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getOperation_mode() {
        return operation_mode;
    }

    public void setOperation_mode(String operation_mode) {
        this.operation_mode = operation_mode;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public String getDateOff() {
        return dateOff;
    }

    public void setDateOff(String dateOff) {
        this.dateOff = dateOff;
    }

    public StaffDTODogovor getStaff() {
        return staff;
    }

    public void setStaff(StaffDTODogovor staff) {
        this.staff = staff;
    }
}
