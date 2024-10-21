package co.edu.unisabana.Notification_Parcial2.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Table
@Entity
public class cliente {

    @Id
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String correo;
    @Column
    private Integer dinero_disponible;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getDinero_disponible() {
        return dinero_disponible;
    }

    public void setDinero_disponible(Integer dinero_disponible) {
        this.dinero_disponible = dinero_disponible;
    }
}
