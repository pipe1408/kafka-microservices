package kafka.Billing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor // Generates a no-args constructor.
@AllArgsConstructor // Generates a constructor with all argumen
public class Cliente {

    @Id
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String correo;

    @Column
    private Double dinero_disponible;


}
