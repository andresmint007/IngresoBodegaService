package com.medisupply.ingresobodega.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class ProducerKafka {

    private static final String TOPIC = "Income";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String mensaje) {
        kafkaTemplate.send(TOPIC, mensaje);
        System.out.println("Mensaje enviado: " + mensaje);
    }
}
