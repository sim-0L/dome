package com.saferoute.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "zona_riesgo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZonaRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreZona;
    private String geolocalizacion;
    private String nivelPeligro;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idCiudad")
    private Ciudad ciudad;
}
