package com.parroquia.service_donaciones.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "donaciones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Donacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDonacion;

    @Column(nullable = false)
    private Integer runDonante;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private String unidadMedida;

    private LocalDateTime fechaIngreso = LocalDateTime.now();

    @Column(nullable = false)
    private String estado = "RECIBIDA";
}