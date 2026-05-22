package com.saferoute.backend.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncidenteResponse {

    private Integer id;
    private String tipoIncidente;
    private LocalDateTime fechaHora;
    private String descripcion;
    private String ubicacion;
    private String usuario;
    private String zonaRiesgo;
}
