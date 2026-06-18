package com.parroquia.service_inventario.service;

import com.parroquia.service_inventario.model.Inventario;
import com.parroquia.service_inventario.model.Movimiento;
import com.parroquia.service_inventario.repository.InventarioRepository;
import com.parroquia.service_inventario.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventarioService {

    @Autowired private InventarioRepository inventarioRepository;
    @Autowired private MovimientoRepository movimientoRepository;

    // CRUD INVENTARIO
    public List<Inventario> listarInventario() {
        return inventarioRepository.findAll();
    }

    public Inventario obtenerItem(Integer id) {
        return inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado en inventario"));
    }

    @Transactional
    public Inventario crearItem(Inventario item) {
        return inventarioRepository.save(item);
    }

    @Transactional
    public void eliminarItem(Integer id) {
        inventarioRepository.deleteById(id);
    }

    // GESTIÓN DE MOVIMIENTOS Y STOCK
    @Transactional
    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Inventario item = inventarioRepository.findById(movimiento.getItem().getIdItem())
                .orElseThrow(() -> new RuntimeException("El item no existe en el inventario"));

        if (movimiento.getTipoMovimiento().equalsIgnoreCase("SALIDA")) {
            if (item.getStockActual() < movimiento.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para realizar la salida");
            }
            item.setStockActual(item.getStockActual() - movimiento.getCantidad());
        } else if (movimiento.getTipoMovimiento().equalsIgnoreCase("ENTRADA")) {
            item.setStockActual(item.getStockActual() + movimiento.getCantidad());
        } else {
            throw new RuntimeException("Tipo de movimiento inválido (Debe ser ENTRADA o SALIDA)");
        }

        inventarioRepository.save(item);
        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> historialPorItem(Integer idItem) {
        return movimientoRepository.findByItem_IdItemOrderByFechaMovimientoDesc(idItem);
    }
}