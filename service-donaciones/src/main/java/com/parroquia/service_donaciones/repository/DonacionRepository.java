package com.parroquia.service_donaciones.repository;

import com.parroquia.service_donaciones.model.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Integer> {
    List<Donacion> findByRunDonante(Integer runDonante);
    List<Donacion> findByEstado(String estado);
}