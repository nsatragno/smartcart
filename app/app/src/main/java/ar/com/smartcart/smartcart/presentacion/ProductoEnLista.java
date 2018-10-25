package ar.com.smartcart.smartcart.presentacion;

import java.math.BigDecimal;

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
}