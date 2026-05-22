package com.saferoute.backend.repository;

import com.saferoute.backend.entity.ZonaRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ZonaRiesgoRepository extends JpaRepository<ZonaRiesgo, Integer> {
    List<ZonaRiesgo> findByCiudadIdCiudad(Integer idCiudad);
}