package com.parroquia.app.reportes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReporte;

    @Column(nullable = false)
    private String nombreReporte;

    @Column(nullable = false)
    private String tipoReporte;

    private LocalDateTime fechaGeneracion = LocalDateTime.now();

    @Column(nullable = false)
    private Integer generadoPorRun;

    @Column(columnDefinition = "TEXT")
    private String datosResumen;
}