package com.saferoute.backend.dto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CiudadResponse {

    private Integer idCiudad;
    private String nombre;
    private String departamento;
    private String codigoPostal;
}