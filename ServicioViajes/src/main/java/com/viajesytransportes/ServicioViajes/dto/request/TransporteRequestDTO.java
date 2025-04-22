package com.viajesytransportes.ServicioViajes.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank; // Updated to jakarta.validation
import jakarta.validation.constraints.NotNull; // Updated to jakarta.validation
import jakarta.validation.constraints.Size; // Updated to jakarta.validation
import java.util.List;

@Data
public class TransporteRequestDTO {

    @NotBlank(message = "Número de placa es obligatorio")
    private String numeroPlaca;

    private String numeroEconomico;

    @NotBlank(message = "Marca es obligatoria")
    private String marca;

    @NotBlank(message = "Modelo es obligatorio")
    private String modelo;

    private String tipoUnidad;

    private String color;

    @NotNull(message = "Número de asientos es obligatorio")
    private Integer numeroAsientos;

    @NotBlank(message = "Estado es obligatorio")
    private String estado;

    @Size(max = 5, message = "Máximo 5 fotografías")
    private List<String> fotografias;

    private String notas;
}