package co.edu.unisabana.shipping.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String correo;

    @Column(precision = 10, scale = 2)
    private Long dineroDisponible;

    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventas;

}
