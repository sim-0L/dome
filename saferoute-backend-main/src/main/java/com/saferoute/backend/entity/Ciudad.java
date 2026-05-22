package com.saferoute.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ciudad")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCiudad;

    private String nombre;
    private String departamento;
    private String codigoPostal;
}