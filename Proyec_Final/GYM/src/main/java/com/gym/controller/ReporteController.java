package com.gym.controller;

import com.gym.repository.PagoRepository;
import com.gym.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    private final PagoRepository pagoRepository;
    private final ClienteRepository clienteRepository;

    public ReporteController(PagoRepository pagoRepository, ClienteRepository clienteRepository) {
        this.pagoRepository = pagoRepository;
        this.clienteRepository = clienteRepository;
    }

    // --- Reporte 1: Ingresos por membresía ---
    @GetMapping("/ingresos-por-membresia")
    public List<Map<String, Object>> ingresosPorMembresia() {
        List<Object[]> resultados = pagoRepository.obtenerIngresosPorMembresia();
        List<Map<String, Object>> respuesta = new ArrayList<>();

        for (Object[] fila : resultados) {
            Map<String, Object> filaMap = new HashMap<>();
            filaMap.put("nombrePlan", fila[0]);
            filaMap.put("totalRecaudado", fila[1]);
            respuesta.add(filaMap);
        }
        return respuesta;
    }

    // --- Reporte 2: Clientes activos por membresía ---
    @GetMapping("/clientes-activos")
    public List<Map<String, Object>> clientesActivosPorMembresia() {
        List<Object[]> resultados = clienteRepository.contarClientesActivosPorMembresia();
        List<Map<String, Object>> respuesta = new ArrayList<>();

        for (Object[] fila : resultados) {
            Map<String, Object> filaMap = new HashMap<>();
            filaMap.put("nombrePlan", fila[0]);
            filaMap.put("clientesActivos", fila[1]);
            respuesta.add(filaMap);
        }
        return respuesta;
    }
}
