package com.devU.devU.controllers;

import com.devU.devU.entities.HistorialCredito;
import com.devU.devU.service.HistorialCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/historial")
public class HistorialController {


    private final HistorialCreditoService historialService;


    @GetMapping
    public List<HistorialCredito> obtenerTodoElHistorial() {
        return historialService.obtenerTodosLosHistoriales();
    }


    @GetMapping("/{id}")
    public ResponseEntity<HistorialCredito> obtenerHistorialPorId(@PathVariable Long id) {
        HistorialCredito historial = historialService.obtenerHistorialPorId(id);
        return ResponseEntity.ok(historial);
    }


    @PostMapping
    public ResponseEntity<HistorialCredito> crearHistorial(@RequestBody HistorialCredito historial) {
        HistorialCredito nuevoHistorial = historialService.guardarHistorial(historial);
        return ResponseEntity.ok(nuevoHistorial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistorial(@PathVariable Long id) {
        historialService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }
}
