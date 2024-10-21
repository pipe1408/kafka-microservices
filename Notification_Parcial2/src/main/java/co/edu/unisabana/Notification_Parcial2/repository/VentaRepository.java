package co.edu.unisabana.Notification_Parcial2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unisabana.Notification_Parcial2.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

}
