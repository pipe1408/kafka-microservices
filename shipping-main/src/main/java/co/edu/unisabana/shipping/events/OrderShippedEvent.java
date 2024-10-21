package co.edu.unisabana.shipping.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderShippedEvent {
    private int orderId;
    private String trackingNumber;
    private String status;

    public OrderShippedEvent(int orderId, String trackingNumber, String status) {
        this.orderId = orderId;
        this.trackingNumber = trackingNumber;
        this.status = status;
    }

}

