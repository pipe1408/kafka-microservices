package co.edu.unisabana.Notification_Parcial2.listner;

import co.edu.unisabana.Notification_Parcial2.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    private final NotificationService notificationService;

    public NotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "PaymentSuccessful")
    public void handlePaymentSuccessfulEvents(String event) {
        notificationService.sendPaymentSuccessNotification(event);
    }

    @KafkaListener(topics = "PaymentFailed")
    public void handlePaymentFailedEvents(String event) {
        notificationService.sendPaymentFailedNotification(event);
    }

    @KafkaListener(topics = "OrderShipped")
    public void handleShippingEvents(String event) {
        notificationService.sendOrderShippedNotification(event);
    }
}
