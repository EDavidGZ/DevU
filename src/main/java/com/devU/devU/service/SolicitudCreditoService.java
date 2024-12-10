package com.devU.devU.service;

import com.devU.devU.entities.Cliente;
import com.devU.devU.entities.SolicitudCredito;
import com.devU.devU.repositories.IClienteRepo;
import com.devU.devU.repositories.ISolicitudCredito;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;


@Service
@RequiredArgsConstructor
public class SolicitudCreditoService {

    private final ISolicitudCredito solicitudCredito;

    private final IClienteRepo clienteRepo;

    public String evaluarSolicitud(SolicitudCredito solicitud) {
        Cliente cliente = solicitud.getCliente();
        LocalDate hoy = LocalDate.now();
        int edad = Period.between(cliente.getFechaNacimiento(), hoy).getYears();

        if (edad < 18) {
            solicitud.setAprobado(false);
            solicitud.setRazonRechazo("El solicitante es menor de edad.");
            solicitudCredito.save(solicitud);
            return "Solicitud rechazada: El solicitante es menor de edad.";
        }

        boolean tieneHistorial = tieneHistorialCrediticio(cliente);


        Double limiteCredito = calcularLimiteCredito(cliente.getIngresosMensuales(), tieneHistorial);


        if (solicitud.getImporteSolicitado() > limiteCredito) {
            solicitud.setAprobado(false);
            solicitud.setRazonRechazo("El importe solicitado excede el límite permitido.");
            solicitudCredito.save(solicitud);
            return "Solicitud rechazada: Importe solicitado excede el límite permitido.";
        }


        solicitud.setAprobado(true);
        solicitud.setRazonRechazo(null);
        solicitudCredito.save(solicitud);
        return "Solicitud aprobada: Crédito otorgado.";
    }


    private boolean tieneHistorialCrediticio(Cliente cliente) {
        LocalDate haceDosAños = LocalDate.now().minusYears(2);

        return cliente.getSolicitudes().stream()
                .anyMatch(solicitud -> solicitud.getAprobado() &&
                        solicitud.getFechaSolicitud().isAfter(haceDosAños));
    }

    private Double calcularLimiteCredito(Double ingresosMensuales, boolean tieneHistorial) {
        if (ingresosMensuales >= 5000 && ingresosMensuales < 10000) {
            return tieneHistorial ? 15000.0 : 7500.0;
        } else if (ingresosMensuales >= 10000 && ingresosMensuales < 20000) {
            return tieneHistorial ? 25000.0 : 12000.0;
        } else if (ingresosMensuales >= 20000 && ingresosMensuales < 40000) {
            return tieneHistorial ? 50000.0 : 30000.0;
        } else if (ingresosMensuales >= 40000) {
            return tieneHistorial ? 100000.0 : 50000.0;
        }
        return 0.0;
    }

}
