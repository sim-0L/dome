package com.saferoute.backend.controller;

import com.saferoute.backend.dto.request.IncidenteRequest;
import com.saferoute.backend.dto.response.IncidenteResponse;
import com.saferoute.backend.service.IncidenteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/incidentes")
public class IncidenteController {

    private final IncidenteService service;

    public IncidenteController(IncidenteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<IncidenteResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenteResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<IncidenteResponse>> listarPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(service.listarPorUsuario(idUsuario));
    }

    @GetMapping("/zona/{idZonaRiesgo}")
    public ResponseEntity<List<IncidenteResponse>> listarPorZona(@PathVariable Integer idZonaRiesgo) {
        return ResponseEntity.ok(service.listarPorZona(idZonaRiesgo));
    }

    @PostMapping
    public ResponseEntity<IncidenteResponse> guardar(@Valid @RequestBody IncidenteRequest req) {
        return ResponseEntity.ok(service.guardar(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidenteResponse> actualizar(@PathVariable Integer id,
                                                        @Valid @RequestBody IncidenteRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
