package com.saferoute.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "configuracion_notificaciones")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ConfiguracionNotificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConfiguracion;

    private Boolean alertasProximidad;
    private Boolean notificacionesPush;
    private Boolean sonidoAlerta;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
}