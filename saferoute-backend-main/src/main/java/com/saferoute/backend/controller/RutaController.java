package com.saferoute.backend.controller;

import com.saferoute.backend.dto.request.RutaRequest;
import com.saferoute.backend.dto.response.RutaResponse;
import com.saferoute.backend.service.RutaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {

    private final RutaService service;

    public RutaController(RutaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RutaResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutaResponse> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<RutaResponse>> listarPorUsuario(
            @PathVariable Integer idUsuario) {
        return ResponseEntity.ok(service.listarPorUsuario(idUsuario));
    }

    @PostMapping
    public ResponseEntity<RutaResponse> crear(
            @Valid @RequestBody RutaRequest req) {
        return ResponseEntity.ok(service.guardar(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RutaResponse> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody RutaRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok("Ruta eliminada correctamente");
    }
}