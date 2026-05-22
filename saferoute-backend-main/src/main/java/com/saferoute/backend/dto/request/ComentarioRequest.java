package com.saferoute.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ComentarioRequest {

    @NotBlank(message = "El contenido es obligatorio")
    private String contenido;

    @NotNull(message = "El foro es obligatorio")
    private Integer idForo;

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUsuario;
}
