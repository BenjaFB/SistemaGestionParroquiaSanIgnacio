package com.parroquia.service_usuarios.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "staff")
@PrimaryKeyJoinColumn(name = "run")
@Getter @Setter @NoArgsConstructor
public class Staff extends Usuario {
    private String cargo;
    private String unidadDepartamento;
}
