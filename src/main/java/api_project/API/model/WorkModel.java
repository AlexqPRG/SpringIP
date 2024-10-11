package api_project.API.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class WorkModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String nameOfWork;

    //связь с таблицей сотрудники
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffModel staff;

//    //связь с таблицей платежи
//    @OneToOne(optional = false, mappedBy = "work")
//    private PaymentModel payment;

    public WorkModel(){}

    public WorkModel(UUID id, String nameOfWork, StaffModel staff) {
        this.id = id;
        this.nameOfWork = nameOfWork;
        this.staff = staff;
//        this.payment = payment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNameOfWork() {
        return nameOfWork;
    }

    public void setNameOfWork(String nameOfWork) {
        this.nameOfWork = nameOfWork;
    }

    public StaffModel getStaff() {
        return staff;
    }

    public void setStaff(StaffModel staff) {
        this.staff = staff;
    }
//
//    public PaymentModel getPayment() {
//        return payment;
//    }
//
//    public void setPayment(PaymentModel payment) {
//        this.payment = payment;
//    }
}
