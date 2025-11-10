package com.gym.repository;

import com.gym.model.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {
    List<Entrenamiento> findByClienteClienteId(Long clienteId);
}
