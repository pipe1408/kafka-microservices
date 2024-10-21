package com.Inventory.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryEventProducer {

    private static final String TOPIC_RESERVED = "InventoryReserved"; // Topic para el evento InventoryReserved
    private static final String TOPIC_FAILED = "InventoryFailed"; // Topic para el evento InventoryFailed

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // Método para enviar el evento InventoryReserved
    public void sendInventoryReservedEvent(InventoryReservedEvent event) {
        kafkaTemplate.send(TOPIC_RESERVED, event);
        System.out.println("Evento InventoryReserved enviado: " + event);
    }

    // Método para enviar el evento InventoryFailed
    public void sendInventoryFailedEvent(InventoryFailedEvent event) {
        kafkaTemplate.send(TOPIC_FAILED, event);
        System.out.println("Evento InventoryFailed enviado: " + event);
    }
}

