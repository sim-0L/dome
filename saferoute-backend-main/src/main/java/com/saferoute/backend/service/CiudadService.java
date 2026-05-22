package com.saferoute.backend.service;

import com.saferoute.backend.dto.request.CiudadRequest;
import com.saferoute.backend.dto.response.CiudadResponse;
import com.saferoute.backend.entity.Ciudad;
import com.saferoute.backend.repository.CiudadRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CiudadService {

    private final CiudadRepository repository;

    public CiudadService(CiudadRepository repository) {
        this.repository = repository;
    }

    public List<CiudadResponse> listar() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CiudadResponse buscarPorId(Integer id) {
        Ciudad c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        return toResponse(c);
    }

    public CiudadResponse guardar(CiudadRequest req) {
        Ciudad c = new Ciudad();
        c.setNombre(req.getNombre());
        c.setDepartamento(req.getDepartamento());
        c.setCodigoPostal(req.getCodigoPostal());
        return toResponse(repository.save(c));
    }

    public CiudadResponse actualizar(Integer id, CiudadRequest req) {
        Ciudad c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        c.setNombre(req.getNombre());
        c.setDepartamento(req.getDepartamento());
        c.setCodigoPostal(req.getCodigoPostal());
        return toResponse(repository.save(c));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private CiudadResponse toResponse(Ciudad c) {
        return new CiudadResponse(
                c.getIdCiudad(),
                c.getNombre(),
                c.getDepartamento(),
                c.getCodigoPostal()
        );
    }
}