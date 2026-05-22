package com.saferoute.backend.controller;

import com.saferoute.backend.dto.request.UsuarioRequest;
import com.saferoute.backend.dto.response.UsuarioResponse;
import com.saferoute.backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
    @GetMapping("/rol/{nombreRol}")
    public ResponseEntity<List<UsuarioResponse>> listarPorRol(@PathVariable String nombreRol) {
        return ResponseEntity.ok(service.listarPorRol(nombreRol));
    }

    @GetMapping("/fecha-registro")
    public ResponseEntity<List<UsuarioResponse>> listarPorFecha(
            @RequestParam("fecha") LocalDate fecha) {
        return ResponseEntity.ok(service.listarActivosPorFecha(fecha));
    }
}