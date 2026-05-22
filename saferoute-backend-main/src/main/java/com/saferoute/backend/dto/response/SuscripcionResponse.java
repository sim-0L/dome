package com.saferoute.backend.dto.response;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SuscripcionResponse {
    private Integer id;
    private String tipoPlan;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estadoSuscripcion;
    private String nombreUsuario;
}