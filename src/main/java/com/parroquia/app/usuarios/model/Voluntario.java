package com.parroquia.app.usuarios.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "voluntarios")
@PrimaryKeyJoinColumn(name = "run")
@Getter @Setter @NoArgsConstructor
public class Voluntario extends Usuario {
    private LocalDate fechaPostulacion;
    private String areasInteres;
    private String disponibilidad;
}