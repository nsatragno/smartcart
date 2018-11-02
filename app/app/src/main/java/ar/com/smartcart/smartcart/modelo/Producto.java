package ar.com.smartcart.smartcart.modelo;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Objects;

public class Producto implements Comparable{

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Boolean apto_celiacos = Boolean.FALSE;
    private Boolean apto_diabeticos = Boolean.FALSE;
    private Categoria categoria;
    private String url;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Boolean getApto_celiacos() {
        return apto_celiacos;
    }

    public void setApto_celiacos(Boolean apto_celiacos) {
        this.apto_celiacos = apto_celiacos;
    }

    public Boolean getApto_diabeticos() {
        return apto_diabeticos;
    }

    public void setApto_diabeticos(Boolean apto_diabeticos) {
        this.apto_diabeticos = apto_diabeticos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int compareTo(@NonNull Object obj) {
        return this.getId().compareTo(((Producto)obj).getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}