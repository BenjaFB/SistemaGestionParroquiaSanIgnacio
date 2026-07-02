package com.parroquia.app.notificaciones.repository;

import com.parroquia.app.notificaciones.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByRunDestinatarioOrderByFechaEnvioDesc(Integer runDestinatario);
    List<Notificacion> findByRunDestinatarioAndLeidoFalse(Integer runDestinatario);
}