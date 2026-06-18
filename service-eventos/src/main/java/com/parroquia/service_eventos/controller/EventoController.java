package com.parroquia.service_eventos.controller;

import com.parroquia.service_eventos.model.Evento;
import com.parroquia.service_eventos.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired 
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<Evento> crear(@Valid @RequestBody Evento evento) {
        return ResponseEntity.ok(eventoService.crearEvento(evento));
    }

    @GetMapping
    public ResponseEntity<List<Evento>> listar() {
        return ResponseEntity.ok(eventoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(eventoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> editar(@PathVariable Integer id, @RequestBody Evento evento) {
        return ResponseEntity.ok(eventoService.actualizarEvento(id, evento));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Evento> actualizarEstado(@PathVariable Integer id, @RequestParam String estado) {
        return ResponseEntity.ok(eventoService.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        eventoService.eliminarEvento(id);
        return ResponseEntity.ok("Evento eliminado correctamente.");
    }
}