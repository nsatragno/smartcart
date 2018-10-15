package ar.com.smartcart.smartcart.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;

public class Chango {
    private Long id;
    private String codigo;
    private ArrayList<ProductoEnLista> productos;

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
        if(productos == null){
            productos = new ArrayList<ProductoEnLista>();
        }
        return productos;
    }

    public void setProductos(ArrayList<ProductoEnLista> productos) {
        this.productos = productos;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ProductoEnLista prod : getProductos()) {
            total = total.add(prod.getSubtotal());
        }
        return total;
    }

    public Long getCantidadTotal() {
        Long cant = 0L;
        for (ProductoEnLista prod : getProductos()) {
            cant += (prod.getCantidad());
        }
        return cant;
    }
}