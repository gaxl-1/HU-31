package com.viajesytransportes.ServicioViajes.controller;

import com.viajesytransportes.ServicioViajes.dto.request.TransporteRequestDTO;
import com.viajesytransportes.ServicioViajes.dto.response.TransporteResponseDTO;
import com.viajesytransportes.ServicioViajes.service.TransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List; // Añadido para manejar la lista

@RestController
@RequestMapping("/api/transportes")
public class TransporteController {

    @Autowired
    private TransporteService transporteService;

    @PostMapping
    public ResponseEntity<TransporteResponseDTO> crearTransporte(@Valid @RequestBody TransporteRequestDTO request) {
        TransporteResponseDTO response = transporteService.crearTransporte(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransporteResponseDTO> actualizarTransporte(@PathVariable Long id, @Valid @RequestBody TransporteRequestDTO request) {
        TransporteResponseDTO response = transporteService.actualizarTransporte(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTransporte(@PathVariable Long id) {
        transporteService.eliminarTransporte(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransporteResponseDTO> obtenerTransporte(@PathVariable Long id) {
        TransporteResponseDTO response = transporteService.obtenerTransporte(id);
        return ResponseEntity.ok(response);
    }

    // Nuevo método para listar todos los transportes
    @GetMapping
    public ResponseEntity<List<TransporteResponseDTO>> listarTransportes() {
        List<TransporteResponseDTO> transportes = transporteService.listarTransportes();
        return ResponseEntity.ok(transportes);
    }
}