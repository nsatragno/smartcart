package ar.com.smartcart.smartcart.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;

public class ListaUsuario {
    private Long id;
    private String nombre;
    private Boolean activa = Boolean.FALSE;
    private List<ProductoEnLista> productos = new ArrayList<ProductoEnLista>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public List<ProductoEnLista> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoEnLista> productos) {
        this.productos = productos;
    }

    public Long getCantEnChango() {
        Long cant = 0L;
        for(ProductoEnLista prod : getProductos()){
            cant += prod.getEnChango() ? prod.getCantidad() : 0L;
        }
        return cant;
    }

    public Long getCantRestante() {
        Long cant = 0L;
        for(ProductoEnLista prod : getProductos()){
            cant += !prod.getEnChango() ? prod.getCantidad() : 0L;
        }
        return cant;
    }
}