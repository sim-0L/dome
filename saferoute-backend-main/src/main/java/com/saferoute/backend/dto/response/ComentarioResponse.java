package com.saferoute.backend.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ComentarioResponse {

    private Integer idComentario;
    private String contenido;
    private LocalDateTime fechaPublicacion;
    private String nombreUsuario;
    private Integer idForo;
    private String tituloForo;
}
