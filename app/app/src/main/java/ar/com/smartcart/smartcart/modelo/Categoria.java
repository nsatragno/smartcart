package ar.com.smartcart.smartcart.modelo;

import java.math.BigDecimal;

public class Categoria {
    private Long id;
    private String nombre;
    private BigDecimal posicion_x;
    private BigDecimal posicion_y;
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

    public BigDecimal getPosicion_x() {
        return posicion_x;
    }

    public void setPosicion_x(BigDecimal posicion_x) {
        this.posicion_x = posicion_x;
    }

    public BigDecimal getPosicion_y() {
        return posicion_y;
    }

    public void setPosicion_y(BigDecimal posicion_y) {
        this.posicion_y = posicion_y;
    }
}
