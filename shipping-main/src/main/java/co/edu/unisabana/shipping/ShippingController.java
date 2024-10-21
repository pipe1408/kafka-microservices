package co.edu.unisabana.shipping;

import co.edu.unisabana.shipping.entities.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final ShippingService shippingService;

    @Autowired
    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/ship")
    public ResponseEntity<String> shipOrder(@RequestBody int orderId) {
        Optional<Venta> ventaOpt = shippingService.getVentaById(orderId);

        if (ventaOpt.isPresent()) {
            Venta venta = ventaOpt.get();
            shippingService.processShipping(venta);
            return ResponseEntity.ok("Proceso de envio iniciado para la orden: " + orderId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Orden :"+orderId+" no encontrada");
        }
    }
}
