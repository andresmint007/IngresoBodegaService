package com.medisupply.ingresobodega.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class ProducerKafka {

    @Value("${kafka.topic.inventario}")
    private String topic;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String mensaje) {
        kafkaTemplate.send(topic, mensaje);
        System.out.println("Mensaje enviado: " + mensaje);
    }
}
