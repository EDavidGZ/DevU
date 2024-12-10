package com.devU.devU.controllers;

import com.devU.devU.entities.SolicitudCredito;
import com.devU.devU.service.SolicitudCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudCreditoController {

    private final SolicitudCreditoService solicitudCreditoService;

    @PostMapping
    public ResponseEntity<String> crearSolicitud(@RequestBody SolicitudCredito solicitud) {
        String resultado = solicitudCreditoService.evaluarSolicitud(solicitud);
        return ResponseEntity.ok(resultado);
    }
}