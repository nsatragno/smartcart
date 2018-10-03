package ar.com.smartcart.smartcart.modelo;

import java.util.ArrayList;
import java.util.List;

import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;

public class ListaUsuario {
    private Long id;
    private String nombre;
    private Boolean activa = Boolean.FALSE;
    private List<ProductoEnLista> productos = new ArrayList<ProductoEnLista>();
    private Long cantidad;

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

    public Long getCantidad() {
        return cantidad;
    }
}