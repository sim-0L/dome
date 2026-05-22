package com.saferoute.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RutaRequest {

    @NotBlank(message = "El origen es obligatorio")
    private String origen;

    @NotBlank(message = "El destino es obligatorio")
    private String destino;

    private Double distancia;
    private String duracionEstimada;
    private String estadoSeguridad;

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUsuario;
}