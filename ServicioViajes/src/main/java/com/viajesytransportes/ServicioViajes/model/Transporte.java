package com.viajesytransportes.ServicioViajes.model;

import lombok.Data;
import jakarta.persistence.*; // Updated to jakarta.persistence
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Transporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numeroPlaca; // Mandatory

    private String numeroEconomico;

    @Column(nullable = false)
    private String marca; // Mandatory

    @Column(nullable = false)
    private String modelo; // Mandatory

    private String tipoUnidad;

    private String color;

    @Column(nullable = false)
    private Integer numeroAsientos; // Mandatory

    @Column(nullable = false)
    private String estado; // Mandatory (Activo, En mantenimiento, Baja)

    @Column(nullable = false)
    private LocalDate fechaAlta; // Set in logic

    @ElementCollection
    @Column(length = 1000)
    private List<String> fotografias; // URLs or file paths, max 5

    private String notas;
}