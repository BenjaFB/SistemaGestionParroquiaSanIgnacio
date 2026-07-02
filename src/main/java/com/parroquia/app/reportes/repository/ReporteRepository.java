package com.parroquia.app.reportes.repository;

import com.parroquia.app.reportes.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
    List<Reporte> findByTipoReporte(String tipoReporte);
    List<Reporte> findByGeneradoPorRun(Integer run);
}