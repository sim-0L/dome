package com.saferoute.backend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZonaRiesgoResponse {

    private Integer id;
    private String nombreZona;
    private String geolocalizacion;
    private String nivelPeligro;
    private String descripcion;
    private String ciudad;
}
