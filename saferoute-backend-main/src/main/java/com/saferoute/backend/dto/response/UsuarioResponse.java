package com.saferoute.backend.dto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UsuarioResponse {

    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String idioma;
    private String nombreRol;
}