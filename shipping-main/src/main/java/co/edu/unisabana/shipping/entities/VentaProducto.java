package co.edu.unisabana.shipping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(VentaProductoId.class)
public class VentaProducto {
    @Id
    @Column(name = "id_venta")
    private Integer idVenta;

    @Id
    @Column(name = "id_producto")
    private Integer idProducto;


    @ManyToOne
    @JoinColumn(name = "id_compra", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    private Integer cantidad;

    // Getters y Setters
}
