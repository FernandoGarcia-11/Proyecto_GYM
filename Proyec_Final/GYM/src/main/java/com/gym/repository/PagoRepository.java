package com.gym.repository;

import com.gym.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    @Query("SELECT p.membresia.nombrePlan, SUM(p.monto) FROM Pago p GROUP BY p.membresia.nombrePlan")
    List<Object[]> obtenerIngresosPorMembresia();
}
