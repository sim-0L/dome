package com.saferoute.backend.repository;

import com.saferoute.backend.entity.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SuscripcionRepository extends JpaRepository<Suscripcion, Integer> {
    List<Suscripcion> findByUsuarioIdUsuario(Integer idUsuario);
}