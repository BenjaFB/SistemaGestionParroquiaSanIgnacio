package com.parroquia.service_notificaciones.controller;

import com.parroquia.service_notificaciones.model.Notificacion;
import com.parroquia.service_notificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired 
    private NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<Notificacion> enviar(@RequestBody Notificacion notificacion) {
        return ResponseEntity.ok(notificacionService.crearNotificacion(notificacion));
    }

    @GetMapping("/usuario/{run}")
    public ResponseEntity<List<Notificacion>> historial(@PathVariable Integer run) {
        return ResponseEntity.ok(notificacionService.listarPorUsuario(run));
    }

    @GetMapping("/usuario/{run}/pendientes")
    public ResponseEntity<List<Notificacion>> pendientes(@PathVariable Integer run) {
        return ResponseEntity.ok(notificacionService.listarNoLeidas(run));
    }

    @PatchMapping("/{id}/leer")
    public ResponseEntity<String> leer(@PathVariable Integer id) {
        notificacionService.marcarComoLeida(id);
        return ResponseEntity.ok("Notificación marcada como leída");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id) {
        notificacionService.eliminar(id);
        return ResponseEntity.ok("Notificación eliminada");
    }
}