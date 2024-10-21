package co.edu.unisabana.shipping.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class VentaProductoId implements Serializable {

    private Integer idVenta;
    private Integer idProducto;

    public VentaProductoId() {}

    public VentaProductoId(Integer idVenta, Integer idProducto) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VentaProductoId that = (VentaProductoId) o;
        return Objects.equals(idVenta, that.idVenta) &&
                Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenta, idProducto);
    }
}

