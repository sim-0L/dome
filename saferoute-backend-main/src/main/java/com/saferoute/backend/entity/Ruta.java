package com.saferoute.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ruta")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRuta;

    private String origen;
    private String destino;
    private Double distancia;
    private String duracionEstimada;
    private String estadoSeguridad;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
}