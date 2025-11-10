package com.gym.controller;

import com.gym.model.Entrenamiento;
import com.gym.repository.EntrenamientoRepository;
import com.gym.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/entrenamientos")
public class EntrenamientoController {

    private final EntrenamientoRepository entrenamientoRepo;
    private final ClienteRepository clienteRepo;

    public EntrenamientoController(EntrenamientoRepository entrenamientoRepo, ClienteRepository clienteRepo) {
        this.entrenamientoRepo = entrenamientoRepo;
        this.clienteRepo = clienteRepo;
    }

    // Crear entrenamiento
    @PostMapping
    public ResponseEntity<Entrenamiento> crear(@RequestBody Entrenamiento e) {
        var cliente = clienteRepo.findById(e.getCliente().getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        e.setCliente(cliente);
        Entrenamiento guardado = entrenamientoRepo.save(e);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // Listar todos los entrenamientos
    @GetMapping
    public ResponseEntity<List<Entrenamiento>> listar() {
        return ResponseEntity.ok(entrenamientoRepo.findAll());
    }

    // Buscar entrenamiento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Entrenamiento> buscarPorId(@PathVariable Long id) {
        return entrenamientoRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar entrenamientos por cliente
    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Entrenamiento>> listarPorCliente(@PathVariable Long id) {
        return ResponseEntity.ok(entrenamientoRepo.findByClienteClienteId(id));
    }
}
