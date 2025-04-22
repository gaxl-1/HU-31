package com.viajesytransportes.ServicioViajes.service;

import com.viajesytransportes.ServicioViajes.dto.request.TransporteRequestDTO;
import com.viajesytransportes.ServicioViajes.dto.response.TransporteResponseDTO;

import java.util.List;

public interface TransporteService {

    TransporteResponseDTO crearTransporte(TransporteRequestDTO request);

    TransporteResponseDTO actualizarTransporte(Long id, TransporteRequestDTO request);

    void eliminarTransporte(Long id);

    TransporteResponseDTO obtenerTransporte(Long id);

    // Nuevo método para listar todos los transportes
    List<TransporteResponseDTO> listarTransportes();
}