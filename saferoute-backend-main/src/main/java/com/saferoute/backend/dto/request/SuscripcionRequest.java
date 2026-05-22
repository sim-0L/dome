package com.saferoute.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SuscripcionRequest {

    @NotBlank(message = "El tipo de plan es obligatorio")
    private String tipoPlan;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @NotBlank(message = "El estado es obligatorio")
    private String estadoSuscripcion;

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUsuario;
}