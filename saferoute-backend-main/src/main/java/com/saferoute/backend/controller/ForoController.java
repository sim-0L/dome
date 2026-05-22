package com.saferoute.backend.controller;

import com.saferoute.backend.dto.request.ForoRequest;
import com.saferoute.backend.dto.response.ForoResponse;
import com.saferoute.backend.service.ForoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/foros")
public class ForoController {

    private final ForoService service;

    public ForoController(ForoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ForoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForoResponse> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ForoResponse> crear(
            @Valid @RequestBody ForoRequest req) {
        return ResponseEntity.ok(service.guardar(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForoResponse> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ForoRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok("Foro eliminado correctamente");
    }
}
