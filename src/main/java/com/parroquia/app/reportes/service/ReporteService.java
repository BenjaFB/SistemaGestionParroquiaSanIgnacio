package com.parroquia.app.reportes.service;

import com.parroquia.app.reportes.model.Reporte;
import com.parroquia.app.reportes.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReporteService {

    @Autowired 
    private ReporteRepository reporteRepository;

    @Transactional
    public Reporte guardarHistorialReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public List<Reporte> listarHistorial() {
        return reporteRepository.findAll();
    }

    public Reporte obtenerPorId(Integer id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
    }

    public List<Reporte> listarPorTipo(String tipo) {
        return reporteRepository.findByTipoReporte(tipo);
    }

    @Transactional
    public void eliminarReporte(Integer id) {
        reporteRepository.deleteById(id);
    }
}