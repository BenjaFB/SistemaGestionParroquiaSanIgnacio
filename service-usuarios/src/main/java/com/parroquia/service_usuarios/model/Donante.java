package com.parroquia.service_usuarios.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "donantes")
@PrimaryKeyJoinColumn(name = "run")
@Getter @Setter @NoArgsConstructor
public class Donante extends Usuario {
    private String tipoDonante;
    private String direccion;
}