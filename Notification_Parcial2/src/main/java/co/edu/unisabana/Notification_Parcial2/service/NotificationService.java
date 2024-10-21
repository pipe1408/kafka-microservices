package co.edu.unisabana.Notification_Parcial2.service;

import co.edu.unisabana.Notification_Parcial2.entity.Venta;
import co.edu.unisabana.Notification_Parcial2.repository.CustomerRepository;
import co.edu.unisabana.Notification_Parcial2.repository.VentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Logger;


@Service
public class NotificationService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VentaRepository ventaRepository;

    private static final Logger logger = Logger.getLogger(NotificationService.class.getName());

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendPaymentSuccessNotification(String event) {
        Integer id = extractCustomerId(event);
        String email = getEmailByCustomerId(id);
        String message = "Your payment was successful! Your order is being processed.";

        sendEmail(email, "Payment Confirmation", message);
        logger.info("El correo se mando correctamente");
    }

    public void sendPaymentFailedNotification(String event) {
        Integer id = extractCustomerId(event);
        String email = getEmailByCustomerId(id);
        String message = "Unfortunately, your payment failed. Please try again.";

        sendEmail(email, "Payment Failed", message);
        logger.info("El correo fallo");
    }

    public void sendOrderShippedNotification(String event) {
        Integer orderId = extractOrderId(event);
        Integer customerId = getCustomerIdByOrderId(orderId);
        String email = getEmailByCustomerId(customerId);
        String message = "Your order has been shipped! Tracking Number: ";

        sendEmail(email, "Order Shipped", message);
        logger.info("La orden fue despachada");
    }


    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    private Integer extractCustomerId(String event) {
        try {
            JsonNode jsonNode = objectMapper.readTree(event);
            return jsonNode.get("customerId").asInt();
        } catch (Exception e) {
            System.err.println("Failed to extract customerId from event: " + event);
            System.err.println("Error details: " + e.getMessage());
            return null;
        }
    }

    private Integer extractOrderId(String event) {
        try {
            JsonNode jsonNode = objectMapper.readTree(event);
            return jsonNode.get("orderId").asInt();
        } catch (Exception e) {
            System.err.println("Failed to extract orderId from event: " + event);
            System.err.println("Error details: " + e.getMessage());
            return null;
        }
    }


    private Integer getCustomerIdByOrderId(Integer orderId) {
        if (orderId == null) {
            System.err.println("CustomerId is null, cannot retrieve email.");
            return null; // o maneja el caso según lo necesites
        }
        Venta venta = ventaRepository.findById(orderId).get();
        return venta.getCliente().getId();
    }

    private String getEmailByCustomerId(Integer customerId) {
        if (customerId == null) {
            System.err.println("CustomerId is null, cannot retrieve email.");
            return null; // o maneja el caso según lo necesites
        }
        return customerRepository.findEmailById(customerId);
    }



}
