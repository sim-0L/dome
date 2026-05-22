package com.saferoute.backend.controller;

import com.saferoute.backend.dto.request.ZonaRiesgoRequest;
import com.saferoute.backend.dto.response.ZonaRiesgoResponse;
import com.saferoute.backend.service.ZonaRiesgoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/zonas")
public class ZonaRiesgoController {

    private final ZonaRiesgoService service;

    public ZonaRiesgoController(ZonaRiesgoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ZonaRiesgoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZonaRiesgoResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/ciudad/{idCiudad}")
    public ResponseEntity<List<ZonaRiesgoResponse>> listarPorCiudad(@PathVariable Integer idCiudad) {
        return ResponseEntity.ok(service.listarPorCiudad(idCiudad));
    }

    @PostMapping
    public ResponseEntity<ZonaRiesgoResponse> guardar(@Valid @RequestBody ZonaRiesgoRequest req) {
        return ResponseEntity.ok(service.guardar(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZonaRiesgoResponse> actualizar(@PathVariable Integer id,
                                                         @Valid @RequestBody ZonaRiesgoRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
