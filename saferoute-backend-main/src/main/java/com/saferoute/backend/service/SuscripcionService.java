package com.saferoute.backend.service;

import com.saferoute.backend.dto.request.SuscripcionRequest;
import com.saferoute.backend.dto.response.SuscripcionResponse;
import com.saferoute.backend.entity.Suscripcion;
import com.saferoute.backend.entity.Usuario;
import com.saferoute.backend.repository.SuscripcionRepository;
import com.saferoute.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuscripcionService {

    private final SuscripcionRepository repository;
    private final UsuarioRepository usuarioRepository;

    public SuscripcionService(SuscripcionRepository repository,
                              UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<SuscripcionResponse> listar() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public SuscripcionResponse buscarPorId(Integer id) {
        Suscripcion s = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripcion no encontrada"));
        return toResponse(s);
    }

    public List<SuscripcionResponse> listarPorUsuario(Integer idUsuario) {
        return repository.findByUsuarioIdUsuario(idUsuario).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public SuscripcionResponse guardar(SuscripcionRequest req) {
        Usuario usuario = usuarioRepository.findById(req.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Suscripcion s = new Suscripcion();
        s.setTipoPlan(req.getTipoPlan());
        s.setFechaInicio(req.getFechaInicio());
        s.setFechaFin(req.getFechaFin());
        s.setEstadoSuscripcion(req.getEstadoSuscripcion());
        s.setUsuario(usuario);
        return toResponse(repository.save(s));
    }

    public SuscripcionResponse actualizar(Integer id, SuscripcionRequest req) {
        Suscripcion s = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripcion no encontrada"));
        s.setTipoPlan(req.getTipoPlan());
        s.setFechaInicio(req.getFechaInicio());
        s.setFechaFin(req.getFechaFin());
        s.setEstadoSuscripcion(req.getEstadoSuscripcion());
        return toResponse(repository.save(s));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private SuscripcionResponse toResponse(Suscripcion s) {
        return new SuscripcionResponse(
                s.getId(), s.getTipoPlan(),
                s.getFechaInicio(), s.getFechaFin(),
                s.getEstadoSuscripcion(),
                s.getUsuario().getNombre()
        );
    }
}