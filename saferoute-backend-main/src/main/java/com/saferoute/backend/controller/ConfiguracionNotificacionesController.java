package com.saferoute.backend.controller;

import com.saferoute.backend.dto.request.ConfiguracionNotificacionesRequest;
import com.saferoute.backend.dto.response.ConfiguracionNotificacionesResponse;
import com.saferoute.backend.service.ConfiguracionNotificacionesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/configuraciones")
public class ConfiguracionNotificacionesController {

    private final ConfiguracionNotificacionesService service;

    public ConfiguracionNotificacionesController(
            ConfiguracionNotificacionesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ConfiguracionNotificacionesResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfiguracionNotificacionesResponse> buscar(
            @PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ConfiguracionNotificacionesResponse> buscarPorUsuario(
            @PathVariable Integer idUsuario) {
        return ResponseEntity.ok(service.buscarPorUsuario(idUsuario));
    }

    @PostMapping
    public ResponseEntity<ConfiguracionNotificacionesResponse> crear(
            @Valid @RequestBody ConfiguracionNotificacionesRequest req) {
        return ResponseEntity.ok(service.guardar(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfiguracionNotificacionesResponse> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ConfiguracionNotificacionesRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok("Configuracion eliminada correctamente");
    }
}