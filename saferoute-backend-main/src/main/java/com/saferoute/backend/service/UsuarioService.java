package com.saferoute.backend.service;

import com.saferoute.backend.dto.request.UsuarioRequest;
import com.saferoute.backend.dto.response.UsuarioResponse;
import com.saferoute.backend.entity.Rol;
import com.saferoute.backend.entity.Usuario;
import com.saferoute.backend.repository.RolRepository;
import com.saferoute.backend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponse> listar() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponse buscarPorId(Integer id) {
        Usuario u = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toResponse(u);
    }

    // El método de guardar/registrar normalmente lo maneja tu AuthService,
    // pero aquí tienes el de actualizar para el mantenimiento.

    public UsuarioResponse actualizar(Integer id, UsuarioRequest req) {
        Usuario u = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepository.findById(req.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        u.setNombre(req.getNombre());
        u.setApellido(req.getApellido());
        u.setEmail(req.getEmail());
        u.setTelefono(req.getTelefono());
        u.setIdioma(req.getIdioma());
        u.setRol(rol);

        // Solo actualizamos contraseña si envían una nueva
        if (req.getContrasenia() != null && !req.getContrasenia().isBlank()) {
            u.setContrasenia(passwordEncoder.encode(req.getContrasenia()));
        }

        return toResponse(repository.save(u));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
    public List<UsuarioResponse> listarPorRol(String nombreRol) {
        return repository.findByRol_NombreIgnoreCase(nombreRol).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<UsuarioResponse> listarActivosPorFecha(LocalDate fecha) {
        return repository.buscarUsuariosActivosPorFecha(fecha).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(
                u.getIdUsuario(),
                u.getNombre(),
                u.getApellido(),
                u.getEmail(),
                u.getTelefono(),
                u.getIdioma(),
                u.getRol() != null ? u.getRol().getNombre() : "SIN ROL"
        );

    }
}
