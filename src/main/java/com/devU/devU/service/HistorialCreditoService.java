package com.devU.devU.service;


import com.devU.devU.entities.Cliente;
import com.devU.devU.entities.HistorialCredito;
import com.devU.devU.exceptons.ResourceNotFoundException;
import com.devU.devU.repositories.IClienteRepo;
import com.devU.devU.repositories.IHistorialCredito;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HistorialCreditoService {

    private final IHistorialCredito historialRepository;

    private final IClienteRepo clienteRepo;

    public List<HistorialCredito> obtenerTodosLosHistoriales() {
        return historialRepository.findAll();
    }


    public HistorialCredito guardarHistorial(HistorialCredito historial) {
        if (historial.getCliente() == null || historial.getCliente().getId() == null) {
            throw new IllegalArgumentException("Cliente asociado no puede ser nulo");
        }

        Cliente cliente = clienteRepo.findById(historial.getCliente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        historial.setCliente(cliente);
        return historialRepository.save(historial);
    }

    public HistorialCredito obtenerHistorialPorId(Long id) {
        return historialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de historial no encontrado con ID: " + id));
    }

    public void eliminarHistorial(Long id) {
        HistorialCredito historial = obtenerHistorialPorId(id);
        historialRepository.delete(historial);
    }
}
