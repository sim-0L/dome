package com.saferoute.backend.repository;

import com.saferoute.backend.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    List<Comentario> findByForoIdForo(Integer idForo);
}