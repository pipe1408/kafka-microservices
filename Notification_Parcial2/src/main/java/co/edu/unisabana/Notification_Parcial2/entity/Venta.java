package co.edu.unisabana.Notification_Parcial2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private cliente idCliente;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "metodo_pago", length = 50)
    private String metodoPago;

    @Column(name = "fecha", nullable = false)
    private Instant fecha;

    public cliente getCliente() {
        return idCliente;
    }
}