package com.medisupply.ingresobodega.controller;

import com.medisupply.ingresobodega.services.ProducerKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private ProducerKafka producer;

    @GetMapping("/send")
    public String sendMsg(@RequestParam("msg") String mensaje) {
        producer.sendMessage(mensaje);
        return "Mensaje enviado: " + mensaje;
    }
}
