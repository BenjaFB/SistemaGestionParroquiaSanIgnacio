package com.parroquia.app.institucion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sedes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSede;

    @Column(nullable = false)
    private String nombreSede;

    @Column(nullable = false)
    private String direccion;

    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_institucion", nullable = false)
    private Institucion institucion;
}