package com.saferoute.backend.controller;

import com.saferoute.backend.dto.request.ComentarioRequest;
import com.saferoute.backend.dto.response.ComentarioResponse;
import com.saferoute.backend.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioService service;

    public ComentarioController(ComentarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ComentarioResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioResponse> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/foro/{idForo}")
    public ResponseEntity<List<ComentarioResponse>> listarPorForo(
            @PathVariable Integer idForo) {
        return ResponseEntity.ok(service.listarPorForo(idForo));
    }

    @PostMapping
    public ResponseEntity<ComentarioResponse> crear(
            @Valid @RequestBody ComentarioRequest req) {
        return ResponseEntity.ok(service.guardar(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioResponse> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ComentarioRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok("Comentario eliminado correctamente");
    }
}
