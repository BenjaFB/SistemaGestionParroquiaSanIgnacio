package com.parroquia.service_inventario.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_item", nullable = false)
    private Inventario item;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private String tipoMovimiento; // ENTRADA o SALIDA

    private LocalDateTime fechaMovimiento = LocalDateTime.now();

    @Column(nullable = false)
    private Integer responsableRun;

    @Column(columnDefinition = "TEXT")
    private String comentario;
}