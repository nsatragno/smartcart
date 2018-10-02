package ar.com.smartcart.smartcart.modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaProductos {
    private Long id;
    private String nombre;
    private Boolean activa = Boolean.FALSE;
    private List<Producto> productos = new ArrayList<Producto>();

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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
