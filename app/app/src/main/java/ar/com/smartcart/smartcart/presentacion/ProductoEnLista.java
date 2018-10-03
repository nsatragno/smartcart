package ar.com.smartcart.smartcart.presentacion;

import java.math.BigDecimal;

import ar.com.smartcart.smartcart.modelo.Producto;

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