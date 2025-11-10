package com.gym.dto;

import jakarta.validation.constraints.*;
import java.util.Date;

public class ClienteDTO {
    private Long clienteId;

    @NotNull
    private Long dpi;

    @NotBlank
    private String nombreCliente;

    private String apellidoCliente;

    @Email
    private String correoCliente;

    @NotBlank
    private String telefonoCliente;

    @Past
    private Date fechaNacimientoCliente;

    private Boolean estadoDeMembresia;

    private Long membresiaId; // FK simple

    // getters y setters

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getDpi() {
        return dpi;
    }

    public void setDpi(Long dpi) {
        this.dpi = dpi;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public Date getFechaNacimientoCliente() {
        return fechaNacimientoCliente;
    }

    public void setFechaNacimientoCliente(Date fechaNacimientoCliente) {
        this.fechaNacimientoCliente = fechaNacimientoCliente;
    }

    public Boolean getEstadoDeMembresia() {
        return estadoDeMembresia;
    }

    public void setEstadoDeMembresia(Boolean estadoDeMembresia) {
        this.estadoDeMembresia = estadoDeMembresia;
    }

    public Long getMembresiaId() {
        return membresiaId;
    }

    public void setMembresiaId(Long membresiaId) {
        this.membresiaId = membresiaId;
    }

    // (genera con IDE o usa Lombok si lo prefieres)
}
