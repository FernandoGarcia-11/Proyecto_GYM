package com.gym.mapper;

import com.gym.dto.ClienteDTO;
import com.gym.model.Cliente;

public class ClienteMapper {

    public static ClienteDTO toDto(Cliente c) {
        if (c == null) return null;
        ClienteDTO dto = new ClienteDTO();
        dto.setClienteId(c.getClienteId());
        dto.setDpi(c.getDpi());
        dto.setNombreCliente(c.getNombreCliente());
        dto.setApellidoCliente(c.getApellidoCliente());
        dto.setCorreoCliente(c.getCorreoCliente());
        dto.setTelefonoCliente(c.getTelefonoCliente());
        dto.setFechaNacimientoCliente(c.getFechaNacimientoCliente());
        dto.setEstadoDeMembresia(c.getEstadoDeMembresia());
        dto.setMembresiaId(c.getMembresia() != null ? c.getMembresia().getMembresiaId() : null);
        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        if (dto == null) return null;
        Cliente c = new Cliente();
        c.setClienteId(dto.getClienteId());
        c.setDpi(dto.getDpi());
        c.setNombreCliente(dto.getNombreCliente());
        c.setApellidoCliente(dto.getApellidoCliente());
        c.setCorreoCliente(dto.getCorreoCliente());
        c.setTelefonoCliente(dto.getTelefonoCliente());
        c.setFechaNacimientoCliente(dto.getFechaNacimientoCliente());
        c.setEstadoDeMembresia(dto.getEstadoDeMembresia());
        // membresia se asigna en el servicio
        return c;
    }
}


