package ar.com.smartcart.smartcart.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;

import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;

public class Chango {
    private Long id;
    private String codigo;
    private ArrayList<ProductoEnLista> productos;
    private BigDecimal subtotal = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<ProductoEnLista> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<ProductoEnLista> productos) {
        this.productos = productos;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}