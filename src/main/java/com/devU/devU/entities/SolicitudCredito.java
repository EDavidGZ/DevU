package com.devU.devU.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class SolicitudCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private Double importeSolicitado;

    @Column(nullable = false)
    private Boolean aprobado;

    @Column(nullable = false)
    private LocalDate fechaSolicitud;

    @Column(length = 255)
    private String razonRechazo;

}
