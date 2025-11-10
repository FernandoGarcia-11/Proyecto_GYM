package com.gym.controller;

import com.gym.model.Membresia;
import com.gym.repository.MembresiaRepository;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/membresias")
public class MembresiaController {

    private final MembresiaRepository repository;

    public MembresiaController(MembresiaRepository repository) {
        this.repository = repository;
    }

    // ✅ Endpoint: listar con paginación y ordenamiento
    @GetMapping
    public Page<Membresia> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "membresiaId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membresia> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Membresia crear(@RequestBody Membresia membresia) {
        return repository.save(membresia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membresia> actualizar(@PathVariable Long id, @RequestBody Membresia nueva) {
        return repository.findById(id)
                .map(m -> {
                    m.setNombrePlan(nueva.getNombrePlan());
                    m.setPrecioPlan(nueva.getPrecioPlan());
                    m.setDuracionDeMesesPlan(nueva.getDuracionDeMesesPlan());
                    m.setDescripcion(nueva.getDescripcion());
                    return ResponseEntity.ok(repository.save(m));
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
