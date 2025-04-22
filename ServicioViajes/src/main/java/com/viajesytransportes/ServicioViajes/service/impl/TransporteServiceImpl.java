package com.viajesytransportes.ServicioViajes.service.impl;

import com.viajesytransportes.ServicioViajes.dto.request.TransporteRequestDTO;
import com.viajesytransportes.ServicioViajes.dto.response.TransporteResponseDTO;
import com.viajesytransportes.ServicioViajes.exception.CustomExceptions;
import com.viajesytransportes.ServicioViajes.model.Transporte;
import com.viajesytransportes.ServicioViajes.repository.TransporteRepository;
import com.viajesytransportes.ServicioViajes.service.TransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import com.viajesytransportes.ServicioViajes.saga.event.TransporteEventType;
import com.viajesytransportes.ServicioViajes.saga.producer.TransporteEventProducer;

import java.util.List; // Añadido para manejar la lista
import java.util.stream.Collectors; // Añadido para usar stream y map

@Service
public class TransporteServiceImpl implements TransporteService {

    @Autowired
    private TransporteRepository transporteRepository;

    @Autowired
    private TransporteEventProducer eventProducer;

    @Override
    public TransporteResponseDTO crearTransporte(TransporteRequestDTO request) {
        if (transporteRepository.existsByNumeroPlaca(request.getNumeroPlaca())) {
            throw new CustomExceptions.ConflictException("El número de placa ya existe");
        }

        Transporte transporte = new Transporte();
        mapRequestToEntity(request, transporte);
        transporte.setFechaAlta(LocalDate.now());

        Transporte savedTransporte = transporteRepository.save(transporte);
        eventProducer.sendTransporteEvent(savedTransporte.getId(), TransporteEventType.CREATED, "Transporte creado");
        return mapEntityToResponse(savedTransporte);
    }

    @Override
    public TransporteResponseDTO actualizarTransporte(Long id, TransporteRequestDTO request) {
        Transporte transporte = transporteRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.NotFoundException("Transporte no encontrado"));

        if (!transporte.getNumeroPlaca().equals(request.getNumeroPlaca()) &&
                transporteRepository.existsByNumeroPlaca(request.getNumeroPlaca())) {
            throw new CustomExceptions.ConflictException("El número de placa ya existe");
        }

        mapRequestToEntity(request, transporte);
        Transporte updatedTransporte = transporteRepository.save(transporte);
        eventProducer.sendTransporteEvent(updatedTransporte.getId(), TransporteEventType.UPDATED, "Transporte actualizado");
        return mapEntityToResponse(updatedTransporte);
    }

    @Override
    public void eliminarTransporte(Long id) {
        Transporte transporte = transporteRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.NotFoundException("Transporte no encontrado"));
        transporteRepository.delete(transporte);
        eventProducer.sendTransporteEvent(id, TransporteEventType.DELETED, "Transporte eliminado");
    }

    @Override
    public TransporteResponseDTO obtenerTransporte(Long id) {
        Transporte transporte = transporteRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.NotFoundException("Transporte no encontrado"));
        return mapEntityToResponse(transporte);
    }

    // Nuevo método para listar todos los transportes
    @Override
    public List<TransporteResponseDTO> listarTransportes() {
        List<Transporte> transportes = transporteRepository.findAll();
        return transportes.stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    private void mapRequestToEntity(TransporteRequestDTO request, Transporte transporte) {
        transporte.setNumeroPlaca(request.getNumeroPlaca());
        transporte.setNumeroEconomico(request.getNumeroEconomico());
        transporte.setMarca(request.getMarca());
        transporte.setModelo(request.getModelo());
        transporte.setTipoUnidad(request.getTipoUnidad());
        transporte.setColor(request.getColor());
        transporte.setNumeroAsientos(request.getNumeroAsientos());
        transporte.setEstado(request.getEstado());
        transporte.setFotografias(request.getFotografias());
        transporte.setNotas(request.getNotas());
    }

    private TransporteResponseDTO mapEntityToResponse(Transporte transporte) {
        TransporteResponseDTO response = new TransporteResponseDTO();
        response.setId(transporte.getId());
        response.setNumeroPlaca(transporte.getNumeroPlaca());
        response.setNumeroEconomico(transporte.getNumeroEconomico());
        response.setMarca(transporte.getMarca());
        response.setModelo(transporte.getModelo());
        response.setTipoUnidad(transporte.getTipoUnidad());
        response.setColor(transporte.getColor());
        response.setNumeroAsientos(transporte.getNumeroAsientos());
        response.setEstado(transporte.getEstado());
        response.setFechaAlta(transporte.getFechaAlta());
        response.setFotografias(transporte.getFotografias());
        response.setNotas(transporte.getNotas());
        return response;
    }
}