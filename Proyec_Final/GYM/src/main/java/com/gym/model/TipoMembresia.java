package com.gym.model;

import java.math.BigDecimal;

public enum TipoMembresia {
    BASICO("Plan Básico", new BigDecimal("120.00"), 1),
    BLACK("Plan Black", new BigDecimal("200.00"), 3),
    GOLD("Plan Gold", new BigDecimal("250.00"), 6);

    private final String descripcion;
    private final BigDecimal precio;
    private final int duracionMeses;

    TipoMembresia(String descripcion, BigDecimal precio, int duracionMeses) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionMeses = duracionMeses;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }
}
