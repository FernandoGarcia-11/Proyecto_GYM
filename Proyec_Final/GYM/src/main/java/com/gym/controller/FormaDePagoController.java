package com.gym.controller;

import com.gym.model.Cliente;
import com.gym.model.FormaDePago;
import com.gym.repository.ClienteRepository;
import com.gym.repository.FormaDePagoRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/formas-de-pago")
public class FormaDePagoController {

    private final FormaDePagoRepository repository;
    private final ClienteRepository clienteRepo;

    public FormaDePagoController(FormaDePagoRepository repository, ClienteRepository clienteRepo) {
        this.repository = repository;
        this.clienteRepo = clienteRepo;
    }

    // === Listar todas las formas de pago ===
    @GetMapping
    public List<FormaDePago> listar() {
        return repository.findAll();
    }

    // === Buscar por ID ===
    @GetMapping("/{id}")
    public ResponseEntity<FormaDePago> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // === Crear una nueva forma de pago ===
    @PostMapping
    public ResponseEntity<FormaDePago> crear(@Valid @RequestBody FormaDePago f,
                                             @RequestParam(required = false) Long clienteId) {
        Cliente cliente = null;

        // Si no viene cliente en el JSON, intenta asignar uno por parámetro (por ejemplo clienteId=3)
        if (f.getCliente() == null || f.getCliente().getClienteId() == null) {
            if (clienteId != null) {
                cliente = clienteRepo.findById(clienteId)
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));
            } else {
                throw new RuntimeException("Debe asociar un cliente existente a la forma de pago");
            }
            f.setCliente(cliente);
        }

        FormaDePago saved = repository.save(f);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // === Actualizar forma de pago existente ===
    @PutMapping("/{id}")
    public ResponseEntity<FormaDePago> actualizar(@PathVariable Long id, @RequestBody FormaDePago nueva) {
        return repository.findById(id)
                .map(fp -> {
                    fp.setTipoTarjeta(nueva.getTipoTarjeta());
                    fp.setNit(nueva.getNit());
                    fp.setNumeroTarjeta(nueva.getNumeroTarjeta());
                    fp.setVencimiento(nueva.getVencimiento());
                    return ResponseEntity.ok(repository.save(fp));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // === Eliminar forma de pago ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

