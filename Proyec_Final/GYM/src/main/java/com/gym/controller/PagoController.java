package com.gym.controller;

import com.gym.model.Pago;
import com.gym.repository.PagoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    private final PagoRepository repository;

    public PagoController(PagoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Pago> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pago crear(@RequestBody Pago pago) {
        return repository.save(pago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizar(@PathVariable Long id, @RequestBody Pago nuevo) {
        return repository.findById(id)
                .map(p -> {
                    p.setFechaPago(nuevo.getFechaPago());
                    p.setMonto(nuevo.getMonto());
                    p.setReferencia(nuevo.getReferencia());
                    p.setEstado(nuevo.getEstado());
                    p.setCliente(nuevo.getCliente());
                    p.setFormaDePago(nuevo.getFormaDePago());
                    p.setMembresia(nuevo.getMembresia());
                    return ResponseEntity.ok(repository.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}