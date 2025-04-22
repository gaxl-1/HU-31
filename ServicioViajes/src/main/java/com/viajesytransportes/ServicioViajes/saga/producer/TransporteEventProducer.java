package com.viajesytransportes.ServicioViajes.saga.producer;

import com.viajesytransportes.ServicioViajes.saga.event.TransporteEvent;
import com.viajesytransportes.ServicioViajes.saga.event.TransporteEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransporteEventProducer {

    private static final Logger logger = LoggerFactory.getLogger(TransporteEventProducer.class);

    @Autowired(required = false)  // Make KafkaTemplate optional
    private KafkaTemplate<String, TransporteEvent> kafkaTemplate;

    private static final String TOPIC = "transporte-events";

    public void sendTransporteEvent(Long transporteId, TransporteEventType eventType, String data) {
        TransporteEvent event = new TransporteEvent();
        event.setTransporteId(transporteId);
        event.setEventType(eventType);
        event.setData(data);

        if (kafkaTemplate == null) {
            logger.warn("KafkaTemplate is not available. Skipping event publishing: topic={}, event={}", TOPIC, event);
            return;
        }

        try {
            kafkaTemplate.send(TOPIC, event);
            logger.info("Evento enviado a Kafka: topic={}, event={}", TOPIC, event);
        } catch (Exception e) {
            logger.error("Error al enviar evento a Kafka: topic={}, error={}", TOPIC, e.getMessage(), e);
        }
    }
}