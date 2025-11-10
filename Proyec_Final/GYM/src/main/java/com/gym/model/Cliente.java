package com.gym.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    @NotNull
    private Long dpi;

    @NotBlank
    private String nombreCliente;

    @NotBlank
    private String apellidoCliente;

    @Email
    @NotBlank
    private String correoCliente;

    @NotBlank
    private String telefonoCliente;

    @Temporal(TemporalType.DATE)
    private Date fechaRegistroCliente;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoCliente;

    private Boolean estadoDeMembresia;

    @ManyToOne
    @JoinColumn(name = "membresia_id", nullable = false)
    private Membresia membresia;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pago> pagos;

    // Getters y setters
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public Long getDpi() { return dpi; }
    public void setDpi(Long dpi) { this.dpi = dpi; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getApellidoCliente() { return apellidoCliente; }
    public void setApellidoCliente(String apellidoCliente) { this.apellidoCliente = apellidoCliente; }

    public String getCorreoCliente() { return correoCliente; }
    public void setCorreoCliente(String correoCliente) { this.correoCliente = correoCliente; }

    public String getTelefonoCliente() { return telefonoCliente; }
    public void setTelefonoCliente(String telefonoCliente) { this.telefonoCliente = telefonoCliente; }

    public Date getFechaRegistroCliente() { return fechaRegistroCliente; }
    public void setFechaRegistroCliente(Date fechaRegistroCliente) { this.fechaRegistroCliente = fechaRegistroCliente; }

    public Date getFechaNacimientoCliente() { return fechaNacimientoCliente; }
    public void setFechaNacimientoCliente(Date fechaNacimientoCliente) { this.fechaNacimientoCliente = fechaNacimientoCliente; }

    public Boolean getEstadoDeMembresia() { return estadoDeMembresia; }
    public void setEstadoDeMembresia(Boolean estadoDeMembresia) { this.estadoDeMembresia = estadoDeMembresia; }

    public Membresia getMembresia() { return membresia; }
    public void setMembresia(Membresia membresia) { this.membresia = membresia; }
}
