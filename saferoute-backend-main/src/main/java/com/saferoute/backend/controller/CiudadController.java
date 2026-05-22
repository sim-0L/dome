package com.saferoute.backend.controller;

import com.saferoute.backend.dto.request.CiudadRequest;
import com.saferoute.backend.dto.response.CiudadResponse;
import com.saferoute.backend.service.CiudadService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    private final CiudadService service;

    public CiudadController(CiudadService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CiudadResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadResponse> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CiudadResponse> crear(
            @Valid @RequestBody CiudadRequest req) {
        return ResponseEntity.ok(service.guardar(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CiudadResponse> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody CiudadRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok("Ciudad eliminada correctamente");
    }
}