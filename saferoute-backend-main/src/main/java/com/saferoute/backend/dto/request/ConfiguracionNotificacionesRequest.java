package com.saferoute.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ConfiguracionNotificacionesRequest {

    @NotNull(message = "El campo alertasProximidad es obligatorio")
    private Boolean alertasProximidad;

    @NotNull(message = "El campo notificacionesPush es obligatorio")
    private Boolean notificacionesPush;

    @NotNull(message = "El campo sonidoAlerta es obligatorio")
    private Boolean sonidoAlerta;

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUsuario;
}