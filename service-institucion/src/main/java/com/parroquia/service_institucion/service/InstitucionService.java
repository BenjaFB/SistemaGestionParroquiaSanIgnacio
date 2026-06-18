package com.parroquia.service_institucion.service;

import com.parroquia.service_institucion.model.Institucion;
import com.parroquia.service_institucion.model.Sede;
import com.parroquia.service_institucion.repository.InstitucionRepository;
import com.parroquia.service_institucion.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstitucionService {

    @Autowired private InstitucionRepository institucionRepository;
    @Autowired private SedeRepository sedeRepository;

    // GESTIÓN INSTITUCIÓN
    public Institucion obtenerInfo() {
        return institucionRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Información institucional no configurada"));
    }

    @Transactional
    public Institucion actualizarInfo(Institucion data) {
        return institucionRepository.save(data);
    }

    // GESTIÓN SEDES
    public List<Sede> listarSedes() {
        return sedeRepository.findAll();
    }

    @Transactional
    public Sede agregarSede(Sede sede) {
        return sedeRepository.save(sede);
    }

    @Transactional
    public void eliminarSede(Integer id) {
        sedeRepository.deleteById(id);
    }
}