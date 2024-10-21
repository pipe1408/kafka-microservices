package co.edu.unisabana.shipping.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentSuccessfulEvent {
    private int orderId;
    private int customerId;
    private double totalAmount;
    private String status;

}
