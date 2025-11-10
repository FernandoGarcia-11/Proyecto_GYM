package com.gym.controller;

import com.gym.dto.ClienteDTO;
import com.gym.mapper.ClienteMapper;
import com.gym.model.Cliente;
import com.gym.model.Membresia;
import com.gym.repository.ClienteRepository;
import com.gym.repository.MembresiaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepo;
    private final MembresiaRepository membresiaRepo;

    public ClienteController(ClienteRepository clienteRepo, MembresiaRepository membresiaRepo) {
        this.clienteRepo = clienteRepo;
        this.membresiaRepo = membresiaRepo;
    }

    // ============================
    // LISTAR (paginado)
    // ============================
    @GetMapping
    public Page<ClienteDTO> listar(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "clienteId,desc") String[] sort) {
        Sort sortObj = Sort.by(Sort.Direction.fromString(sort[1]), sort[0]);
        Pageable pageable = PageRequest.of(page, size, sortObj);
        Page<Cliente> p = clienteRepo.findAll(pageable);
        log.info("Listando clientes página {} con tamaño {}", page, size);
        return p.map(ClienteMapper::toDto);
    }

    // ============================
    // BUSCAR POR ID
    // ============================
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscar(@PathVariable Long id) {
        log.info("Buscando cliente con ID={}", id);
        return clienteRepo.findById(id)
                .map(c -> ResponseEntity.ok(ClienteMapper.toDto(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    // ============================
    // CREAR (con membresía automática BASICO)
    // ============================
    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@Valid @RequestBody ClienteDTO dto) {
        log.info("Creando cliente con DPI={} y correo={}", dto.getDpi(), dto.getCorreoCliente());
        Cliente c = ClienteMapper.toEntity(dto);

        if (dto.getMembresiaId() != null) {
            Membresia m = membresiaRepo.findById(dto.getMembresiaId())
                    .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));
            c.setMembresia(m);
        } else {
            // Si no manda membresía, por defecto asigna BASICO
            Membresia membresiaBasica = membresiaRepo.findAll().stream()
                    .filter(m -> m.getNombrePlan().name().equalsIgnoreCase("BASICO"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No existe la membresía 'BASICO' en la base de datos"));
            c.setMembresia(membresiaBasica);
        }

        Cliente saved = clienteRepo.save(c);
        log.info("Cliente creado con ID={}", saved.getClienteId());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteMapper.toDto(saved));
    }

    // ============================
    // ACTUALIZAR
    // ============================
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
        log.info("Actualizando cliente ID={}", id);
        return clienteRepo.findById(id).map(existing -> {
            existing.setNombreCliente(dto.getNombreCliente());
            existing.setApellidoCliente(dto.getApellidoCliente());
            existing.setCorreoCliente(dto.getCorreoCliente());
            existing.setTelefonoCliente(dto.getTelefonoCliente());
            existing.setFechaNacimientoCliente(dto.getFechaNacimientoCliente());
            existing.setDpi(dto.getDpi());
            existing.setEstadoDeMembresia(dto.getEstadoDeMembresia());

            if (dto.getMembresiaId() != null) {
                Membresia m = membresiaRepo.findById(dto.getMembresiaId())
                        .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));
                existing.setMembresia(m);
            }

            Cliente saved = clienteRepo.save(existing);
            log.info("Cliente actualizado con ID={}", saved.getClienteId());
            return ResponseEntity.ok(ClienteMapper.toDto(saved));
        }).orElse(ResponseEntity.notFound().build());
    }

    // ============================
    // ELIMINAR
    // ============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.warn("Eliminando cliente ID={}", id);
        if (!clienteRepo.existsById(id)) return ResponseEntity.notFound().build();
        clienteRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ============================
    // LOGIN (DPI + TELÉFONO)
    // ============================
    @GetMapping("/login")
    public ResponseEntity<ClienteDTO> login(
            @RequestParam Long dpi,
            @RequestParam String telefono) {

        log.info("Intento de login DPI={} TEL={}", dpi, telefono);

        return clienteRepo.findByDpiAndTelefonoCliente(dpi, telefono)
                .map(cliente -> ResponseEntity.ok(ClienteMapper.toDto(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }
}