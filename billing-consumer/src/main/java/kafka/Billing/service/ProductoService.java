package kafka.Billing.service;


import kafka.Billing.entity.Producto;
import kafka.Billing.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    public Producto getById(int id) {
        return productoRepository.findById(id).get();
    }
}
