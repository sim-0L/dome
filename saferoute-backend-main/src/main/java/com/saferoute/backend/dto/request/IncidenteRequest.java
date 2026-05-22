package com.saferoute.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncidenteRequest {

    @NotBlank
    private String tipoIncidente;

    @NotNull
    private LocalDateTime fechaHora;

    private String descripcion;

    @NotBlank
    private String ubicacion;

    @NotNull
    private Integer idUsuario;

    private Integer idZonaRiesgo;
}
