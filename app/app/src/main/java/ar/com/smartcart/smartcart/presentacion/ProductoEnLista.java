package ar.com.smartcart.smartcart.presentacion;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

import ar.com.smartcart.smartcart.modelo.Producto;

public class ProductoEnLista {
    private Producto producto;
    private Long cantidad = 0L;
    private Boolean enChango = Boolean.FALSE;

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public BigDecimal getSubtotal() {
        return producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));
    }

    public Boolean getEnChango() {
        return enChango;
    }

    public void setEnChango(Boolean enChango) {
        this.enChango = enChango;
    }

    public static ProductoEnLista parseEnLista(Producto prod){
        ProductoEnLista pEnLisra = new ProductoEnLista();
        pEnLisra.setProducto(prod);
        pEnLisra.setCantidad(1l);
        pEnLisra.setEnChango(Boolean.FALSE);
        return pEnLisra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoEnLista that = (ProductoEnLista) o;
        return Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto);
    }
}