package com.parroquia.service_reportes.controller;

import com.parroquia.service_reportes.model.Reporte;
import com.parroquia.service_reportes.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired 
    private ReporteService reporteService;

    @PostMapping("/historial")
    public ResponseEntity<Reporte> registrarGeneracion(@RequestBody Reporte reporte) {
        return ResponseEntity.ok(reporteService.guardarHistorialReporte(reporte));
    }

    @GetMapping
    public ResponseEntity<List<Reporte>> listarTodo() {
        return ResponseEntity.ok(reporteService.listarHistorial());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(reporteService.obtenerPorId(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Reporte>> porTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(reporteService.listarPorTipo(tipo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id) {
        reporteService.eliminarReporte(id);
        return ResponseEntity.ok("Registro de reporte eliminado del historial");
    }
}