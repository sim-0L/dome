package com.saferoute.backend.repository;

import com.saferoute.backend.entity.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RutaRepository extends JpaRepository<Ruta, Integer> {
    List<Ruta> findByUsuarioIdUsuario(Integer idUsuario);
}