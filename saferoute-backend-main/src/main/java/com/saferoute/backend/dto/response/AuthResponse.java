package com.saferoute.backend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class AuthResponse {

    private String token;
    private String tipo;
    private String correo;
    private String nombreRol;
}