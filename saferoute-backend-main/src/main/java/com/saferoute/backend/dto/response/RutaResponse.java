package com.saferoute.backend.dto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RutaResponse {

    private Integer idRuta;
    private String origen;
    private String destino;
    private Double distancia;
    private String duracionEstimada;
    private String estadoSeguridad;
    private String nombreUsuario;
}