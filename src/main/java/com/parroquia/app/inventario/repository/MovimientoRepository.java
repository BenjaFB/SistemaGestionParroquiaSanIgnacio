package com.parroquia.app.inventario.repository;

import com.parroquia.app.inventario.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    List<Movimiento> findByItem_IdItemOrderByFechaMovimientoDesc(Integer idItem);
}