package com.viajesytransportes.ServicioViajes.repository;

import com.viajesytransportes.ServicioViajes.model.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransporteRepository extends JpaRepository<Transporte, Long> {
    boolean existsByNumeroPlaca(String numeroPlaca);
}