package co.edu.unisabana.shipping;

import co.edu.unisabana.shipping.events.OrderShippedEvent;
import co.edu.unisabana.shipping.events.PaymentSuccessfulEvent;
import co.edu.unisabana.shipping.entities.Venta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ShippingService {

    private final KafkaTemplate<String, Object> kafkaTemplate;  // Object para manejar diferentes tipos de eventos
    private final VentaRepository ventaRepository;

    @Autowired
    public ShippingService(KafkaTemplate<String, Object> kafkaTemplate,
                           VentaRepository ventaRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.ventaRepository = ventaRepository;
    }

    @KafkaListener(topics = "PaymentSuccessful", groupId = "shipping")
    public void handlePaymentSuccessful(String event) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            PaymentSuccessfulEvent paymentEvent = objectMapper.readValue(event, PaymentSuccessfulEvent.class);
            Optional<Venta> ventaOpt = ventaRepository.findById(paymentEvent.getOrderId());

            if (ventaOpt.isPresent()) {
                Venta venta = ventaOpt.get();
                processShipping(venta);
            } else {
                System.out.println("Venta no encontrada para el orderId: " + paymentEvent.getOrderId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processShipping(Venta venta) {
        String trackingNumber = generateTrackingNumber();
        venta.setEstado("enviado");
        ventaRepository.save(venta);

        kafkaTemplate.send("OrderShipped", new OrderShippedEvent(venta.getIdCompra(), trackingNumber, "shipped"));
    }

    private String generateTrackingNumber() {
        return "TRACK" + System.currentTimeMillis();
    }

    public Optional<Venta> getVentaById(int orderId) {
        return ventaRepository.findById(orderId);
    }
}
