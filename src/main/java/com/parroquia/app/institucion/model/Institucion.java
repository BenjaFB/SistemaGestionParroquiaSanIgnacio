package com.parroquia.app.institucion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "institucion")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Institucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInstitucion;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String mision;

    @Column(columnDefinition = "TEXT")
    private String vision;

    @Column(columnDefinition = "TEXT")
    private String historia;

    private String correoContacto;
    private String telefonoContacto;
}