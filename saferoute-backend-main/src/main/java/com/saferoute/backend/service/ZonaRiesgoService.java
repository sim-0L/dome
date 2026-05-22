package com.saferoute.backend.service;

import com.saferoute.backend.dto.request.ZonaRiesgoRequest;
import com.saferoute.backend.dto.response.ZonaRiesgoResponse;
import com.saferoute.backend.entity.Ciudad;
import com.saferoute.backend.entity.ZonaRiesgo;
import com.saferoute.backend.repository.CiudadRepository;
import com.saferoute.backend.repository.ZonaRiesgoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZonaRiesgoService {

    private final ZonaRiesgoRepository repository;
    private final CiudadRepository ciudadRepository;

    public ZonaRiesgoService(ZonaRiesgoRepository repository,
                             CiudadRepository ciudadRepository) {
        this.repository = repository;
        this.ciudadRepository = ciudadRepository;
    }

    public List<ZonaRiesgoResponse> listar() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ZonaRiesgoResponse buscarPorId(Integer id) {
        ZonaRiesgo z = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zona de riesgo no encontrada"));
        return toResponse(z);
    }

    public List<ZonaRiesgoResponse> listarPorCiudad(Integer idCiudad) {
        return repository.findByCiudadIdCiudad(idCiudad).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ZonaRiesgoResponse guardar(ZonaRiesgoRequest req) {
        Ciudad ciudad = ciudadRepository.findById(req.getIdCiudad())
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        ZonaRiesgo z = new ZonaRiesgo();
        z.setNombreZona(req.getNombreZona());
        z.setGeolocalizacion(req.getGeolocalizacion());
        z.setNivelPeligro(req.getNivelPeligro());
        z.setDescripcion(req.getDescripcion());
        z.setCiudad(ciudad);
        return toResponse(repository.save(z));
    }

    public ZonaRiesgoResponse actualizar(Integer id, ZonaRiesgoRequest req) {
        ZonaRiesgo z = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zona de riesgo no encontrada"));
        z.setNombreZona(req.getNombreZona());
        z.setGeolocalizacion(req.getGeolocalizacion());
        z.setNivelPeligro(req.getNivelPeligro());
        z.setDescripcion(req.getDescripcion());
        return toResponse(repository.save(z));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private ZonaRiesgoResponse toResponse(ZonaRiesgo z) {
        return new ZonaRiesgoResponse(
                z.getId(),
                z.getNombreZona(),
                z.getGeolocalizacion(),
                z.getNivelPeligro(),
                z.getDescripcion(),
                z.getCiudad().getNombre()
        );
    }
}
