package com.saferoute.backend.service;

import com.saferoute.backend.dto.request.*;
import com.saferoute.backend.dto.response.*;
import com.saferoute.backend.entity.*;
import com.saferoute.backend.repository.*;
import com.saferoute.backend.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioRepository usuarioRepository,
                       RolRepository rolRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UsuarioResponse registrar(UsuarioRequest req) {
        if (usuarioRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        Rol rol = rolRepository.findById(req.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario u = new Usuario();
        u.setNombre(req.getNombre());
        u.setApellido(req.getApellido());
        u.setEmail(req.getEmail());
        u.setContrasenia(passwordEncoder.encode(req.getContrasenia()));
        u.setTelefono(req.getTelefono());
        u.setIdioma(req.getIdioma());
        u.setFechaRegistro(LocalDate.now());
        u.setEstado(true);
        u.setRol(rol);

        usuarioRepository.save(u);
        return toResponse(u);
    }

    public AuthResponse login(LoginRequest req) {
        Usuario u = usuarioRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(req.getContrasenia(), u.getContrasenia())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generar(u.getEmail());
        return new AuthResponse(token, "Bearer",
                u.getEmail(), u.getRol().getNombre());
    }

    private UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(
                u.getIdUsuario(), u.getNombre(), u.getApellido(),
                u.getEmail(), u.getTelefono(), u.getIdioma(),
                u.getRol().getNombre()
        );
    }
}