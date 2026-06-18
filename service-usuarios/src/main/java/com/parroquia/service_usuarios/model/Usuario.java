package com.parroquia.service_usuarios.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Usuario {
    @Id
    private Integer run;
    private String dv;
    private String nombre;
    private String appaterno;
    private String apmaterno;
    private String telefono;
    private String correo;
    private String contrasena;
    private String rol; // Manejo de rol como atributo directo
    private Boolean estadoCuenta = true;
}