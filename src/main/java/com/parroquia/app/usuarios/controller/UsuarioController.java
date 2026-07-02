package com.parroquia.app.usuarios.controller;

import com.parroquia.app.usuarios.model.*;
import com.parroquia.app.usuarios.service.UsuarioService;
import com.parroquia.app.usuarios.dto.LoginRequest;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired private UsuarioService usuarioService;

    // Login: valida correo + contrase\u00f1a y devuelve datos b\u00e1sicos del usuario
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        var usuario = usuarioService.login(request.getCorreo(), request.getContrasena());
        return ResponseEntity.ok(Map.of(
                "run", usuario.getRun(),
                "nombre", usuario.getNombre(),
                "appaterno", usuario.getAppaterno() != null ? usuario.getAppaterno() : "",
                "correo", usuario.getCorreo(),
                "rol", usuario.getRol() != null ? usuario.getRol() : ""
        ));
    }

    // Crear polim\u00f3rficamente seg\u00fan el tipo enviado
    @PostMapping("/donante")
    public ResponseEntity<String> registrarDonante(@RequestBody Donante d) {
        d.setRol("ROLE_DONANTE");
        usuarioService.registrar(d);
        return ResponseEntity.ok("Donante registrado");
    }

    @PostMapping("/voluntario")
    public ResponseEntity<String> registrarVoluntario(@RequestBody Voluntario v) {
        v.setRol("ROLE_VOLUNTARIO");
        v.setFechaPostulacion(java.time.LocalDate.now());
        usuarioService.registrar(v);
        return ResponseEntity.ok("Voluntario registrado");
    }

    @PostMapping("/staff")
    public ResponseEntity<String> registrarStaff(@RequestBody Staff s) {
        usuarioService.registrar(s); // El rol viene en el cargo/unidad
        return ResponseEntity.ok("Miembro del Staff registrado");
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{run}")
    public ResponseEntity<Usuario> obtener(@PathVariable Integer run) {
        return ResponseEntity.ok(usuarioService.obtener(run));
    }

    @PutMapping("/{run}")
    public ResponseEntity<String> actualizar(@PathVariable Integer run, @RequestBody Usuario u) {
        usuarioService.actualizar(run, u);
        return ResponseEntity.ok("Usuario actualizado");
    }

    @DeleteMapping("/{run}")
    public ResponseEntity<String> eliminar(@PathVariable Integer run) {
        usuarioService.eliminar(run);
        return ResponseEntity.ok("Usuario eliminado");
    }
}