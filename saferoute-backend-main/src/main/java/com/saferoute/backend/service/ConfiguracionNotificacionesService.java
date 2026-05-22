package com.saferoute.backend.service;

import com.saferoute.backend.dto.request.ConfiguracionNotificacionesRequest;
import com.saferoute.backend.dto.response.ConfiguracionNotificacionesResponse;
import com.saferoute.backend.entity.ConfiguracionNotificaciones;
import com.saferoute.backend.entity.Usuario;
import com.saferoute.backend.repository.ConfiguracionNotificacionesRepository;
import com.saferoute.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfiguracionNotificacionesService {

    private final ConfiguracionNotificacionesRepository repository;
    private final UsuarioRepository usuarioRepository;

    public ConfiguracionNotificacionesService(
            ConfiguracionNotificacionesRepository repository,
            UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ConfiguracionNotificacionesResponse> listar() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ConfiguracionNotificacionesResponse buscarPorId(Integer id) {
        ConfiguracionNotificaciones c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuracion no encontrada"));
        return toResponse(c);
    }

    public ConfiguracionNotificacionesResponse buscarPorUsuario(Integer idUsuario) {
        ConfiguracionNotificaciones c = repository.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Configuracion no encontrada"));
        return toResponse(c);
    }

    public ConfiguracionNotificacionesResponse guardar(
            ConfiguracionNotificacionesRequest req) {
        Usuario usuario = usuarioRepository.findById(req.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        ConfiguracionNotificaciones c = new ConfiguracionNotificaciones();
        c.setAlertasProximidad(req.getAlertasProximidad());
        c.setNotificacionesPush(req.getNotificacionesPush());
        c.setSonidoAlerta(req.getSonidoAlerta());
        c.setUsuario(usuario);
        return toResponse(repository.save(c));
    }

    public ConfiguracionNotificacionesResponse actualizar(Integer id,
                                                          ConfiguracionNotificacionesRequest req) {
        ConfiguracionNotificaciones c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuracion no encontrada"));
        c.setAlertasProximidad(req.getAlertasProximidad());
        c.setNotificacionesPush(req.getNotificacionesPush());
        c.setSonidoAlerta(req.getSonidoAlerta());
        return toResponse(repository.save(c));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private ConfiguracionNotificacionesResponse toResponse(
            ConfiguracionNotificaciones c) {
        return new ConfiguracionNotificacionesResponse(
                c.getIdConfiguracion(),
                c.getAlertasProximidad(),
                c.getNotificacionesPush(),
                c.getSonidoAlerta(),
                c.getUsuario().getNombre()
        );
    }
}