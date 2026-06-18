package com.parroquia.service_institucion.controller;

import com.parroquia.service_institucion.model.Institucion;
import com.parroquia.service_institucion.model.Sede;
import com.parroquia.service_institucion.service.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/institucion")
public class InstitucionController {

    @Autowired private InstitucionService institucionService;

    @GetMapping("/info")
    public ResponseEntity<Institucion> verInfo() {
        return ResponseEntity.ok(institucionService.obtenerInfo());
    }

    @PutMapping("/info")
    public ResponseEntity<Institucion> actualizar(@RequestBody Institucion info) {
        return ResponseEntity.ok(institucionService.actualizarInfo(info));
    }

    @GetMapping("/sedes")
    public ResponseEntity<List<Sede>> sedes() {
        return ResponseEntity.ok(institucionService.listarSedes());
    }

    @PostMapping("/sedes")
    public ResponseEntity<Sede> nuevaSede(@RequestBody Sede sede) {
        return ResponseEntity.ok(institucionService.agregarSede(sede));
    }

    @DeleteMapping("/sedes/{id}")
    public ResponseEntity<String> borrarSede(@PathVariable Integer id) {
        institucionService.eliminarSede(id);
        return ResponseEntity.ok("Sede eliminada");
    }
}