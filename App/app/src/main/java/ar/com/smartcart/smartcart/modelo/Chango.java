package ar.com.smartcart.smartcart.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Chango {
    private Long id;
    private String codigo;
    private ArrayList<ProductoEnLista> productos;
    private BigDecimal subtotal = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<ProductoEnLista> getProductos() {
        return productos;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}