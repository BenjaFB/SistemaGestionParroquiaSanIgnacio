package com.parroquia.app.institucion.repository;

import com.parroquia.app.institucion.model.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
    List<Sede> findByInstitucion_IdInstitucion(Integer idInstitucion);
}