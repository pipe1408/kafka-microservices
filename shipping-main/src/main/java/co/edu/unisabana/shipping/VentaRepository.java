package co.edu.unisabana.shipping;

import co.edu.unisabana.shipping.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
}

