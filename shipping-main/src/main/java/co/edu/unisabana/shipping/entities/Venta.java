package co.edu.unisabana.shipping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;  // El tipo es Integer para corresponder al modelo de la base de datos

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(length = 50, nullable = false)
    private String estado;

    @Column(length = 100, nullable = false)
    private String direccion;

    @Column(length = 50, nullable = false)
    private String metodoPago;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @OneToMany(mappedBy = "venta")
    private List<VentaProducto> ventasProductos;

}
