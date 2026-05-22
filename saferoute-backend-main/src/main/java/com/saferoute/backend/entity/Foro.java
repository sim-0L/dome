package com.saferoute.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "foro")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Foro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idForo;

    private String tituloTema;
    private String categoria;
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
}