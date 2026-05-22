package com.saferoute.backend.service;

import com.saferoute.backend.dto.request.NotificacionRequest;
import com.saferoute.backend.dto.response.NotificacionResponse;
import com.saferoute.backend.entity.Notificacion;
import com.saferoute.backend.entity.Usuario;
import com.saferoute.backend.repository.NotificacionRepository;
import com.saferoute.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificacionService {

    private final NotificacionRepository repository;
    private final UsuarioRepository usuarioRepository;

    public NotificacionService(NotificacionRepository repository,
                               UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<NotificacionResponse> listar() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public NotificacionResponse buscarPorId(Integer id) {
        Notificacion n = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacion no encontrada"));
        return toResponse(n);
    }

    public List<NotificacionResponse> listarPorUsuario(Integer idUsuario) {
        return repository.findByUsuarioIdUsuario(idUsuario).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public NotificacionResponse guardar(NotificacionRequest req) {
        Usuario usuario = usuarioRepository.findById(req.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Notificacion n = new Notificacion();
        n.setTitulo(req.getTitulo());
        n.setMensaje(req.getMensaje());
        n.setFechaEnvio(LocalDateTime.now());
        n.setLeido(false);
        n.setUsuario(usuario);
        return toResponse(repository.save(n));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private NotificacionResponse toResponse(Notificacion n) {
        return new NotificacionResponse(
                n.getIdNotificacion(),
                n.getTitulo(),
                n.getMensaje(),
                n.getFechaEnvio(),
                n.getLeido(),
                n.getUsuario().getNombre()
        );
    }
}