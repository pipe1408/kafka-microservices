package co.edu.unisabana.shipping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(precision = 10, scale = 2)
    private Long valor;

    private Integer cantidad;

    @OneToMany(mappedBy = "producto")
    private List<VentaProducto> ventasProductos;

}
