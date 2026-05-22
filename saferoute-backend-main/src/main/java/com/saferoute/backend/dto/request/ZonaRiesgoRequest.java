package com.saferoute.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZonaRiesgoRequest {

    @NotBlank
    private String nombreZona;

    @NotBlank
    private String geolocalizacion;

    @NotBlank
    private String nivelPeligro;

    private String descripcion;

    @NotNull
    private Integer idCiudad;
}
