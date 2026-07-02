package com.parroquia.app.donaciones.service;

import com.parroquia.app.donaciones.model.Donacion;
import com.parroquia.app.donaciones.repository.DonacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonacionService {

    @Autowired 
    private DonacionRepository donacionRepository;

    @Transactional
    public Donacion registrar(Donacion donacion) {
        // La fecha y el estado inicial se manejan por defecto en el modelo
        return donacionRepository.save(donacion);
    }

    public List<Donacion> listarTodas() {
        return donacionRepository.findAll();
    }

    public Donacion obtenerPorId(Integer id) {
        return donacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donación no encontrada con ID: " + id));
    }

    public List<Donacion> listarPorDonante(Integer runDonante) {
        return donacionRepository.findByRunDonante(runDonante);
    }

    @Transactional
    public Donacion actualizarEstado(Integer id, String nuevoEstado) {
        Donacion d = obtenerPorId(id);
        d.setEstado(nuevoEstado.toUpperCase());
        return donacionRepository.save(d);
    }

    @Transactional
    public Donacion editar(Integer id, Donacion data) {
        Donacion d = obtenerPorId(id);
        d.setCategoria(data.getCategoria());
        d.setDescripcion(data.getDescripcion());
        d.setCantidad(data.getCantidad());
        d.setUnidadMedida(data.getUnidadMedida());
        return donacionRepository.save(d);
    }

    @Transactional
    public void eliminar(Integer id) {
        if(!donacionRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: ID no existe.");
        }
        donacionRepository.deleteById(id);
    }
}