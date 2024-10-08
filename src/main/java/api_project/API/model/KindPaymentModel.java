package api_project.API.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class KindPaymentModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String name; //наименование вида платежа

    //связь с таблией платежи
    @OneToMany(mappedBy = "kindPayment", cascade = CascadeType.ALL)
    private List<PaymentModel> payment;

    public KindPaymentModel(){}

    public KindPaymentModel(UUID id, String name, List<PaymentModel> payment) {
        this.id = id;
        this.name = name;
        this.payment = payment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PaymentModel> getPayment() {
        return payment;
    }

    public void setPayment(List<PaymentModel> payment) {
        this.payment = payment;
    }
}
