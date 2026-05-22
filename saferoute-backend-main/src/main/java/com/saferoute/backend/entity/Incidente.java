package com.saferoute.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tipoIncidente;
    private LocalDateTime fechaHora;
    private String descripcion;
    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idZonaRiesgo")
    private ZonaRiesgo zonaRiesgo;
}
