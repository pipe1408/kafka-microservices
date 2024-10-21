package co.edu.unisabana.Notification_Parcial2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.edu.unisabana.Notification_Parcial2.entity.cliente;

@Repository
public interface CustomerRepository extends JpaRepository<cliente, Integer> {

    @Query("SELECT c.correo FROM cliente c WHERE c.id = :id")
    String findEmailById(@Param("id") Integer id);
}


