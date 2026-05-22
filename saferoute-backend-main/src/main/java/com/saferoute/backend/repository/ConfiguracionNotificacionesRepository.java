package com.saferoute.backend.repository;

import com.saferoute.backend.entity.ConfiguracionNotificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ConfiguracionNotificacionesRepository
        extends JpaRepository<ConfiguracionNotificaciones, Integer> {
    Optional<ConfiguracionNotificaciones> findByUsuarioIdUsuario(Integer idUsuario);
}