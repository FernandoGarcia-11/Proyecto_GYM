package com.gym.repository;

import com.gym.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;   // <--- IMPORTANTE

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("""
           SELECT c.membresia.nombrePlan, COUNT(c)
           FROM Cliente c
           WHERE c.estadoDeMembresia = true
           GROUP BY c.membresia.nombrePlan
           """)
    List<Object[]> contarClientesActivosPorMembresia();

    Optional<Cliente> findByDpiAndTelefonoCliente(Long dpi, String telefonoCliente);
}

