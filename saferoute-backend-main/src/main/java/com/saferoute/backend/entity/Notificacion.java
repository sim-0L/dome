package com.saferoute.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacion")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNotificacion;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaEnvio;
    private Boolean leido;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
}