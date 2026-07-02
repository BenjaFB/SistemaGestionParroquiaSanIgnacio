package com.parroquia.app.donaciones.controller;

import com.parroquia.app.donaciones.model.Donacion;
import com.parroquia.app.donaciones.service.DonacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donaciones")
public class DonacionController {

    @Autowired 
    private DonacionService donacionService;

    @PostMapping
    public ResponseEntity<Donacion> crear(@Valid @RequestBody Donacion donacion) {
        return ResponseEntity.ok(donacionService.registrar(donacion));
    }

    @GetMapping
    public ResponseEntity<List<Donacion>> listar() {
        return ResponseEntity.ok(donacionService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donacion> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(donacionService.obtenerPorId(id));
    }

    @GetMapping("/donante/{run}")
    public ResponseEntity<List<Donacion>> porDonante(@PathVariable Integer run) {
        return ResponseEntity.ok(donacionService.listarPorDonante(run));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Donacion> cambiarEstado(@PathVariable Integer id, @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(donacionService.actualizarEstado(id, nuevoEstado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donacion> editar(@PathVariable Integer id, @RequestBody Donacion donacion) {
        return ResponseEntity.ok(donacionService.editar(id, donacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        donacionService.eliminar(id);
        return ResponseEntity.ok("Donación eliminada correctamente.");
    }
}