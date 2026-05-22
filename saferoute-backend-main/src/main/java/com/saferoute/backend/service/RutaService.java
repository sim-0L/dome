package com.saferoute.backend.service;

import com.saferoute.backend.dto.request.RutaRequest;
import com.saferoute.backend.dto.response.RutaResponse;
import com.saferoute.backend.entity.Ruta;
import com.saferoute.backend.entity.Usuario;
import com.saferoute.backend.repository.RutaRepository;
import com.saferoute.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RutaService {

    private final RutaRepository repository;
    private final UsuarioRepository usuarioRepository;

    public RutaService(RutaRepository repository,
                       UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<RutaResponse> listar() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public RutaResponse buscarPorId(Integer id) {
        Ruta r = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
        return toResponse(r);
    }

    public List<RutaResponse> listarPorUsuario(Integer idUsuario) {
        return repository.findByUsuarioIdUsuario(idUsuario).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public RutaResponse guardar(RutaRequest req) {
        Usuario usuario = usuarioRepository.findById(req.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Ruta r = new Ruta();
        r.setOrigen(req.getOrigen());
        r.setDestino(req.getDestino());
        r.setDistancia(req.getDistancia());
        r.setDuracionEstimada(req.getDuracionEstimada());
        r.setEstadoSeguridad(req.getEstadoSeguridad());
        r.setUsuario(usuario);
        return toResponse(repository.save(r));
    }

    public RutaResponse actualizar(Integer id, RutaRequest req) {
        Ruta r = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
        r.setOrigen(req.getOrigen());
        r.setDestino(req.getDestino());
        r.setDistancia(req.getDistancia());
        r.setDuracionEstimada(req.getDuracionEstimada());
        r.setEstadoSeguridad(req.getEstadoSeguridad());
        return toResponse(repository.save(r));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private RutaResponse toResponse(Ruta r) {
        return new RutaResponse(
                r.getIdRuta(),
                r.getOrigen(),
                r.getDestino(),
                r.getDistancia(),
                r.getDuracionEstimada(),
                r.getEstadoSeguridad(),
                r.getUsuario().getNombre()
        );
    }
}