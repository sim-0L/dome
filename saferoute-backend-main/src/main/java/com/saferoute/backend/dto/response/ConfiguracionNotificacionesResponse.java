package com.saferoute.backend.dto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ConfiguracionNotificacionesResponse {

    private Integer idConfiguracion;
    private Boolean alertasProximidad;
    private Boolean notificacionesPush;
    private Boolean sonidoAlerta;
    private String nombreUsuario;
}