package com.viajesytransportes.ServicioViajes.saga.event;

import lombok.Data;

@Data
public class TransporteEvent {
    private Long transporteId;
    private TransporteEventType eventType;
    private String data;
}