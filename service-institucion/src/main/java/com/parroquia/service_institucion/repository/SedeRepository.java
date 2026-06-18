package com.parroquia.service_institucion.repository;

import com.parroquia.service_institucion.model.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
    List<Sede> findByInstitucion_IdInstitucion(Integer idInstitucion);
}