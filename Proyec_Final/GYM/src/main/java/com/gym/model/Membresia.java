package com.gym.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "membresias")
public class Membresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membresiaId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMembresia nombrePlan;

    @DecimalMin("0.0")
    private BigDecimal precioPlan;

    private int duracionDeMesesPlan;

    private String descripcion;

    @OneToMany(mappedBy = "membresia", cascade = CascadeType.ALL)
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "membresia", cascade = CascadeType.ALL)
    private List<Pago> pagos;

    @PrePersist
    @PreUpdate
    public void asignarPrecioPorPlan() {
        if (nombrePlan != null) {
            this.precioPlan = nombrePlan.getPrecio();
            this.duracionDeMesesPlan = nombrePlan.getDuracionMeses();
            this.descripcion = nombrePlan.getDescripcion();
        }
    }

    public Long getMembresiaId() { return membresiaId; }
    public void setMembresiaId(Long membresiaId) { this.membresiaId = membresiaId; }

    public TipoMembresia getNombrePlan() { return nombrePlan; }
    public void setNombrePlan(TipoMembresia nombrePlan) { this.nombrePlan = nombrePlan; }

    public BigDecimal getPrecioPlan() { return precioPlan; }
    public void setPrecioPlan(BigDecimal precioPlan) { this.precioPlan = precioPlan; }

    public int getDuracionDeMesesPlan() { return duracionDeMesesPlan; }
    public void setDuracionDeMesesPlan(int duracionDeMesesPlan) { this.duracionDeMesesPlan = duracionDeMesesPlan; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

