package api_project.API.model.modeltDTO;

import api_project.API.model.KindPaymentModel;
import api_project.API.model.PaymentModel;

import java.util.UUID;

public class PaymentDTO {
    private UUID id;
    private double sum;
    private String date;
    private KindPaymentDTO kindPayment;
    private ClientDTOPayment client;

    public PaymentDTO(){}

    public PaymentDTO(PaymentModel paymentModel) {
        this.id = paymentModel.getId();
        this.sum = paymentModel.getSum();
        this.date = paymentModel.getDate();
        this.kindPayment = new KindPaymentDTO(paymentModel.getKindPayment());
        this.client = new ClientDTOPayment(paymentModel.getClient());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public KindPaymentDTO getKindPayment() {
        return kindPayment;
    }

    public void setKindPayment(KindPaymentDTO kindPayment) {
        this.kindPayment = kindPayment;
    }

    public ClientDTOPayment getClient() {
        return client;
    }

    public void setClient(ClientDTOPayment client) {
        this.client = client;
    }
}
