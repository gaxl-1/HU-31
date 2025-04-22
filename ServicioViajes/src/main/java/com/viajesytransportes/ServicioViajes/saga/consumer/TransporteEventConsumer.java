package com.viajesytransportes.ServicioViajes.saga.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransporteEventConsumer {

    @KafkaListener(topics = "transporte-events", groupId = "transporte-group")
    public void listen(String message) {
        // Process the event (e.g., update other systems or logs)
        System.out.println("Received event: " + message);
    }
}