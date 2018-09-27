package ar.com.smartcart.smartcart.modelo;

import java.math.BigDecimal;

public class ProductoEnLista {
    private Producto producto;
    private Long cantidad;

    public Long getCantidad() {
        return cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public BigDecimal getSubtotal() {
        return producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));
    }
}