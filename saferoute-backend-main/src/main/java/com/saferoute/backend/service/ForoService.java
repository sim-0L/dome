package com.saferoute.backend.service;

import com.saferoute.backend.dto.request.ForoRequest;
import com.saferoute.backend.dto.response.ForoResponse;
import com.saferoute.backend.entity.Foro;
import com.saferoute.backend.entity.Usuario;
import com.saferoute.backend.repository.ForoRepository;
import com.saferoute.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForoService {

    private final ForoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public ForoService(ForoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ForoResponse> listar() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ForoResponse buscarPorId(Integer id) {
        Foro f = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Foro no encontrado"));
        return toResponse(f);
    }

    public ForoResponse guardar(ForoRequest req) {
        Usuario usuario = usuarioRepository.findById(req.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Foro f = new Foro();
        f.setTituloTema(req.getTituloTema());
        f.setCategoria(req.getCategoria());
        f.setFechaCreacion(LocalDateTime.now());
        f.setUsuario(usuario);
        return toResponse(repository.save(f));
    }

    public ForoResponse actualizar(Integer id, ForoRequest req) {
        Foro f = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Foro no encontrado"));
        f.setTituloTema(req.getTituloTema());
        f.setCategoria(req.getCategoria());
        return toResponse(repository.save(f));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private ForoResponse toResponse(Foro f) {
        return new ForoResponse(
                f.getIdForo(),
                f.getTituloTema(),
                f.getCategoria(),
                f.getFechaCreacion(),
                f.getUsuario().getNombre() + " " + f.getUsuario().getApellido()
        );
    }
}
