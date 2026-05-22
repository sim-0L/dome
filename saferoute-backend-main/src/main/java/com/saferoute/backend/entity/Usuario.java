package com.saferoute.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    private String telefono;
    private String idioma;
    private LocalDate fechaRegistro;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;
}