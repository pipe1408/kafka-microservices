package kafka.Billing.service;

import kafka.Billing.entity.Cliente;
import kafka.Billing.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente getClienteById(int id) {
        return clienteRepository.findById(id).get();
    }
}
