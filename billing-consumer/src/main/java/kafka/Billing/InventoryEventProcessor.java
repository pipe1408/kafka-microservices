package kafka.Billing;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.Billing.config.KafkaProducerConfig;
import kafka.Billing.dto.SelectedProductDTO;
import kafka.Billing.entity.*;
import kafka.Billing.repository.ProductoRepository;
import kafka.Billing.service.ClienteService;
import kafka.Billing.service.ProductoService;
import kafka.Billing.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InventoryEventProcessor {


    private Float finalAmount;
    private Integer orderId;
    private Integer clienteId;


    @Autowired
    private ProductoService productoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private VentaService ventaService;

    private final KafkaProducerConfig kafkaProducerConfig;

    @Autowired
    public InventoryEventProcessor(KafkaProducerConfig kafkaProducerConfig) {
        this.kafkaProducerConfig = kafkaProducerConfig;
    }

    public static InventoryEvent parseInventoryEvent(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, InventoryEvent.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Float calculateFinalAmount(ArrayList<SelectedProductDTO> products) {
        Float finalAmount = 0f;
        for (SelectedProductDTO product : products) {
            Producto productoFinal = productoService.getById((product.getProductId()));
            //System.out.println(productoFinal.getValor() * product.getQuantity());
            finalAmount += productoFinal.getValor() * product.getQuantity();
        }

        return finalAmount;
    }

    public boolean enoughMoneyToBuyProducts(InventoryEvent inventoryEvent){
        Float finalAmount = calculateFinalAmount(inventoryEvent.getProductList());
        this.finalAmount = finalAmount;
        Venta venta = ventaService.getClienteFromOrderId(inventoryEvent.getOrderId());
        System.out.println("este es mi order id " + inventoryEvent.getOrderId());
        Integer clienteId = venta.getIdCliente().getId();
        this.clienteId = clienteId;
        this.orderId = inventoryEvent.getOrderId();
        Double clienteMoney = getClienteMoney(clienteId);
        return clienteMoney >= finalAmount;
    }

    private Double getClienteMoney(Integer clienteId){
        Cliente cliente = clienteService.getClienteById(clienteId);
        return cliente.getDinero_disponible();

    }

    public String makePurchase(InventoryEvent inventoryEvent){
        String status = "failed";
        String topic = "PaymentFailed";
        if (enoughMoneyToBuyProducts(inventoryEvent)){
            status="success";
            topic = "PaymentSuccessful";
        }

        sendTopic(topic, status);

        return status;
    }

    private void sendTopic(String topic, String status){
        kafkaProducerConfig.kafkaTemplate().send(topic, new Payment(this.orderId, this.clienteId, this.finalAmount,status));

    }


}
