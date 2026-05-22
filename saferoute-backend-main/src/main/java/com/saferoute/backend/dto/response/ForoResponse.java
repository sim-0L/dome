package com.saferoute.backend.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ForoResponse {

    private Integer idForo;
    private String tituloTema;
    private String categoria;
    private LocalDateTime fechaCreacion;
    private String nombreUsuario;
}
