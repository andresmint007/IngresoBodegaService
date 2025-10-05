package com.medisupply.ingresobodega.services;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerKafka {

    @KafkaListener(topics = "Inventory", groupId = "grupo1")
    public void listen(String mensaje) {
        System.out.println("Mensaje recibido: " + mensaje);
    }
}
