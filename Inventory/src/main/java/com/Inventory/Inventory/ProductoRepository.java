package com.Inventory.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Método para encontrar un producto por su id
    Optional<Producto> findById(Integer id);
}

