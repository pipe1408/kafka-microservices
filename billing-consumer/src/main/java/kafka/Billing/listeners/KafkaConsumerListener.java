package kafka.Billing.listeners;


import kafka.Billing.InventoryEventProcessor;
import kafka.Billing.entity.InventoryEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;


import static kafka.Billing.InventoryEventProcessor.parseInventoryEvent;


@Configuration
public class KafkaConsumerListener {

    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @Autowired
    private InventoryEventProcessor inventoryEventProcessor;


    @KafkaListener(topics = {"Greet"}, groupId = "Julian", containerFactory = "greetingListener")
    public void greetingListener(String greeting) {
        LOGGER.info("MENSAJE RECIBIDO: " + greeting);
        InventoryEvent event1 = parseInventoryEvent(greeting);
        LOGGER.info(event1.toString());
        System.out.println(inventoryEventProcessor.makePurchase(event1));
    }
}
