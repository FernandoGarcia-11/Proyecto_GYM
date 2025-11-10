package com.gym.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "formas_de_pago")
public class FormaDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formaPagoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTarjeta tipoTarjeta; // DEBITO | CREDITO

    @NotBlank
    @Pattern(regexp = "\\d{16}", message = "El número de tarjeta debe tener 16 dígitos")
    @Column(name = "numero_tarjeta", nullable = false, length = 16)
    private String numeroTarjeta;

    @NotNull
    private LocalDate vencimiento; // formato YYYY-MM-01

    private String nit;

    // Relación con cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Getters y setters
    public Long getFormaPagoId() { return formaPagoId; }
    public void setFormaPagoId(Long formaPagoId) { this.formaPagoId = formaPagoId; }

    public TipoTarjeta getTipoTarjeta() { return tipoTarjeta; }
    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) { this.tipoTarjeta = tipoTarjeta; }

    public String getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }

    public LocalDate getVencimiento() { return vencimiento; }
    public void setVencimiento(LocalDate vencimiento) { this.vencimiento = vencimiento; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}



