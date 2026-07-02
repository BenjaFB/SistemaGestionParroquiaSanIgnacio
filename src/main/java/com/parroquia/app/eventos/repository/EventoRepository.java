package com.parroquia.app.eventos.repository;

import com.parroquia.app.eventos.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByEstadoEvento(String estadoEvento);
}