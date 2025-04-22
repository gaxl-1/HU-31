package com.viajesytransportes.ServicioViajes.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class TransporteResponseDTO {

    private Long id;
    private String numeroPlaca;
    private String numeroEconomico;
    private String marca;
    private String modelo;
    private String tipoUnidad;
    private String color;
    private Integer numeroAsientos;
    private String estado;
    private LocalDate fechaAlta;
    private List<String> fotografias;
    private String notas;
}