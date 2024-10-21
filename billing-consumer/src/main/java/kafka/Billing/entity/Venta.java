package kafka.Billing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente idCliente;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "metodo_pago", length = 50)
    private String metodoPago;

    @Column(name = "fecha", nullable = false)
    private Instant fecha;
}