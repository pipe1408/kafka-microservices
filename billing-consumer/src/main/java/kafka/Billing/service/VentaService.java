package kafka.Billing.service;

import kafka.Billing.entity.Cliente;
import kafka.Billing.entity.Venta;
import kafka.Billing.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    VentaRepository ventaRepository;

    public Venta getClienteFromOrderId(Integer orderId){

        return ventaRepository.findById(orderId).get();
    }
}
