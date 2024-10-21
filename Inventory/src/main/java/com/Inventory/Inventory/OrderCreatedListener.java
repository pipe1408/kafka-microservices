package com.Inventory.Inventory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderCreatedListener {

    private final ObjectMapper mapper;
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private InventoryEventProducer inventoryEventProducer;

    public OrderCreatedListener(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    @KafkaListener(topics = "OrderCreated", groupId = "notification-group")
    public void consume(String message) {
        try {
            // Usar ObjectMapper para convertir el String JSON en un objeto OrderCreatedEvent
            ObjectMapper mapper = new ObjectMapper(); // Asegúrate de tener este bean configurado
            OrderCreatedEvent orderCreatedEvent = mapper.readValue(message, OrderCreatedEvent.class);

            // Ahora puedes trabajar con el objeto deserializado
            List<ProductoDto> productList = orderCreatedEvent.getProductList();
            int orderId = orderCreatedEvent.getOrderId();
            boolean sufficientInventory = true;

            // Verificar inventario para cada producto en la orden
            for (ProductoDto productOrder : productList) {
                Optional<Producto> productoOptional = productoRepository.findById(productOrder.productId());

                if (productoOptional.isPresent()) {
                    Producto producto = productoOptional.get();
                    if (producto.getCantidad() < productOrder.quantity()) {
                        System.out.println("No hay suficiente inventario para el producto: " + producto.getNombre());
                        sufficientInventory = false;
                        break;
                    }
                } else {
                    System.out.println("Producto no encontrado: " + productOrder.productId());
                    sufficientInventory = false;
                    break;
                }
            }

            if (sufficientInventory) {
                // Actualizar el inventario si hay suficiente para todos los productos
                for (ProductoDto productOrder : productList) {
                    Optional<Producto> productoOptional = productoRepository.findById(productOrder.productId());

                    if (productoOptional.isPresent()) {
                        Producto producto = productoOptional.get();
                        producto.setCantidad(producto.getCantidad() - productOrder.quantity());
                        productoRepository.save(producto); // Guardar el stock actualizado
                        System.out.println("Stock actualizado para producto: " + producto.getNombre());
                    }
                }

                // Emitir el evento InventoryReserved
                InventoryReservedEvent inventoryReservedEvent = new InventoryReservedEvent(orderId, productList, "reserved");
                inventoryEventProducer.sendInventoryReservedEvent(inventoryReservedEvent);
            } else {
                // Emitir el evento InventoryFailed si no hay suficiente inventario
                InventoryFailedEvent inventoryFailedEvent = new InventoryFailedEvent(orderId, productList, "failed", "Insufficient stock");
                inventoryEventProducer.sendInventoryFailedEvent(inventoryFailedEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al deserializar el mensaje a OrderCreatedEvent");
        }
    }


}

