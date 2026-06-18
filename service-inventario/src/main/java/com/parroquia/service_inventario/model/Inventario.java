package com.parroquia.service_inventario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventario")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    @Column(nullable = false)
    private String nombreMaterial;

    @Column(nullable = false)
    private String categoria;

    private Integer stockActual = 0;

    @Column(nullable = false)
    private String unidadMedida;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
}