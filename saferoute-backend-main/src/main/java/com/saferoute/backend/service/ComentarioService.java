package com.saferoute.backend.service;

import com.saferoute.backend.dto.request.ComentarioRequest;
import com.saferoute.backend.dto.response.ComentarioResponse;
import com.saferoute.backend.entity.Comentario;
import com.saferoute.backend.entity.Foro;
import com.saferoute.backend.entity.Usuario;
import com.saferoute.backend.repository.ComentarioRepository;
import com.saferoute.backend.repository.ForoRepository;
import com.saferoute.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    private final ComentarioRepository repository;
    private final ForoRepository foroRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioService(
            ComentarioRepository repository,
            ForoRepository foroRepository,
            UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.foroRepository = foroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ComentarioResponse> listar() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ComentarioResponse buscarPorId(Integer id) {
        Comentario c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        return toResponse(c);
    }

    public List<ComentarioResponse> listarPorForo(Integer idForo) {
        return repository.findByForoIdForo(idForo).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ComentarioResponse guardar(ComentarioRequest req) {
        Foro foro = foroRepository.findById(req.getIdForo())
                .orElseThrow(() -> new RuntimeException("Foro no encontrado"));
        Usuario usuario = usuarioRepository.findById(req.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Comentario c = new Comentario();
        c.setContenido(req.getContenido());
        c.setFechaPublicacion(LocalDateTime.now());
        c.setForo(foro);
        c.setUsuario(usuario);
        return toResponse(repository.save(c));
    }

    public ComentarioResponse actualizar(Integer id, ComentarioRequest req) {
        Comentario c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        c.setContenido(req.getContenido());
        return toResponse(repository.save(c));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private ComentarioResponse toResponse(Comentario c) {
        return new ComentarioResponse(
                c.getIdComentario(),
                c.getContenido(),
                c.getFechaPublicacion(),
                c.getUsuario().getNombre() + " " + c.getUsuario().getApellido(),
                c.getForo().getIdForo(),
                c.getForo().getTituloTema()
        );
    }
}
