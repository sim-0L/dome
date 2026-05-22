package com.saferoute.backend.service;

import com.saferoute.backend.dto.request.IncidenteRequest;
import com.saferoute.backend.dto.response.IncidenteResponse;
import com.saferoute.backend.entity.Incidente;
import com.saferoute.backend.entity.Usuario;
import com.saferoute.backend.entity.ZonaRiesgo;
import com.saferoute.backend.repository.IncidenteRepository;
import com.saferoute.backend.repository.UsuarioRepository;
import com.saferoute.backend.repository.ZonaRiesgoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidenteService {

    private final IncidenteRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ZonaRiesgoRepository zonaRiesgoRepository;

    public IncidenteService(IncidenteRepository repository,
                            UsuarioRepository usuarioRepository,
                            ZonaRiesgoRepository zonaRiesgoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.zonaRiesgoRepository = zonaRiesgoRepository;
    }

    public List<IncidenteResponse> listar() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public IncidenteResponse buscarPorId(Integer id) {
        Incidente i = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidente no encontrado"));
        return toResponse(i);
    }

    public List<IncidenteResponse> listarPorUsuario(Integer idUsuario) {
        return repository.findByUsuarioIdUsuario(idUsuario).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<IncidenteResponse> listarPorZona(Integer idZonaRiesgo) {
        return repository.findByZonaRiesgoId(idZonaRiesgo).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public IncidenteResponse guardar(IncidenteRequest req) {
        Usuario usuario = usuarioRepository.findById(req.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ZonaRiesgo zonaRiesgo = null;
        if (req.getIdZonaRiesgo() != null) {
            zonaRiesgo = zonaRiesgoRepository.findById(req.getIdZonaRiesgo())
                    .orElseThrow(() -> new RuntimeException("Zona de riesgo no encontrada"));
        }

        Incidente i = new Incidente();
        i.setTipoIncidente(req.getTipoIncidente());
        i.setFechaHora(req.getFechaHora());
        i.setDescripcion(req.getDescripcion());
        i.setUbicacion(req.getUbicacion());
        i.setUsuario(usuario);
        i.setZonaRiesgo(zonaRiesgo);
        return toResponse(repository.save(i));
    }

    public IncidenteResponse actualizar(Integer id, IncidenteRequest req) {
        Incidente i = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidente no encontrado"));
        i.setTipoIncidente(req.getTipoIncidente());
        i.setFechaHora(req.getFechaHora());
        i.setDescripcion(req.getDescripcion());
        i.setUbicacion(req.getUbicacion());
        return toResponse(repository.save(i));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private IncidenteResponse toResponse(Incidente i) {
        return new IncidenteResponse(
                i.getId(),
                i.getTipoIncidente(),
                i.getFechaHora(),
                i.getDescripcion(),
                i.getUbicacion(),
                i.getUsuario().getNombre(),
                i.getZonaRiesgo() != null ? i.getZonaRiesgo().getNombreZona() : null
        );
    }
}
