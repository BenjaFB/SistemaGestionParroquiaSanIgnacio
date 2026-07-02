package com.parroquia.app.notificaciones.service;

import com.parroquia.app.notificaciones.model.Notificacion;
import com.parroquia.app.notificaciones.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificacionService {

    @Autowired 
    private NotificacionRepository notificacionRepository;

    @Transactional
    public Notificacion crearNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> listarPorUsuario(Integer run) {
        return notificacionRepository.findByRunDestinatarioOrderByFechaEnvioDesc(run);
    }

    public List<Notificacion> listarNoLeidas(Integer run) {
        return notificacionRepository.findByRunDestinatarioAndLeidoFalse(run);
    }

    @Transactional
    public void marcarComoLeida(Integer id) {
        Notificacion n = notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        n.setLeido(true);
        notificacionRepository.save(n);
    }

    @Transactional
    public void eliminar(Integer id) {
        notificacionRepository.deleteById(id);
    }
}