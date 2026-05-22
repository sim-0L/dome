package com.saferoute.backend.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class NotificacionResponse {

    private Integer idNotificacion;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaEnvio;
    private Boolean leido;
    private String nombreUsuario;
}