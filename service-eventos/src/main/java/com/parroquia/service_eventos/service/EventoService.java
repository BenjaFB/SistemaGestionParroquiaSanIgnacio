package com.parroquia.service_eventos.service;

import com.parroquia.service_eventos.model.Evento;
import com.parroquia.service_eventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {

    @Autowired 
    private EventoRepository eventoRepository;

    @Transactional
    public Evento crearEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Evento obtenerPorId(Integer id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));
    }

    @Transactional
    public Evento actualizarEvento(Integer id, Evento data) {
        Evento e = obtenerPorId(id);
        e.setNombreEvento(data.getNombreEvento());
        e.setFecha(data.getFecha());
        e.setDireccion(data.getDireccion());
        e.setDescripcion(data.getDescripcion());
        return eventoRepository.save(e);
    }

    @Transactional
    public Evento cambiarEstado(Integer id, String nuevoEstado) {
        Evento e = obtenerPorId(id);
        e.setEstadoEvento(nuevoEstado.toUpperCase());
        return eventoRepository.save(e);
    }

    @Transactional
    public void eliminarEvento(Integer id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: El evento no existe.");
        }
        eventoRepository.deleteById(id);
    }
}