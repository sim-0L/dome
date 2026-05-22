package com.saferoute.backend.repository;

import com.saferoute.backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);
    @Query("SELECT u FROM Usuario u WHERE u.fechaRegistro= :fecha AND u.estado=true")
    List<Usuario> buscarUsuariosActivosPorFecha(@Param("fecha") LocalDate fecha);
    List<Usuario> findByRol_NombreIgnoreCase(String nombreRol);
}
