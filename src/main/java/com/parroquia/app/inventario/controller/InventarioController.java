package com.parroquia.app.inventario.controller;

import com.parroquia.app.inventario.model.Inventario;
import com.parroquia.app.inventario.model.Movimiento;
import com.parroquia.app.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> listar() {
        return ResponseEntity.ok(inventarioService.listarInventario());
    }

    @PostMapping("/items")
    public ResponseEntity<Inventario> crear(@RequestBody Inventario item) {
        return ResponseEntity.ok(inventarioService.crearItem(item));
    }

    @PostMapping("/movimientos")
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody Movimiento movimiento) {
        return ResponseEntity.ok(inventarioService.registrarMovimiento(movimiento));
    }

    @GetMapping("/items/{id}/historial")
    public ResponseEntity<List<Movimiento>> verHistorial(@PathVariable Integer id) {
        return ResponseEntity.ok(inventarioService.historialPorItem(id));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        inventarioService.eliminarItem(id);
        return ResponseEntity.ok("Item eliminado");
    }
}