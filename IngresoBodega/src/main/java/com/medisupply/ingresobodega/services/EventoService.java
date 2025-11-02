package com.medisupply.ingresobodega.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medisupply.ingresobodega.entities.RegistroBlockchain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.http.HttpTimeoutException;

@Service
public class EventoService {

    @Value("${URLservicioBlockchain}")
    private String urlServicioBlockchain;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public EventoService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String enviarEvento(RegistroBlockchain evento) {
        try {
            String jsonBody = objectMapper.writeValueAsString(evento);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlServicioBlockchain))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode >= 200 && statusCode < 300) {
                return response.body();
            } else {
                return "Error: El servidor respondió con un código HTTP " + statusCode + ". Mensaje: " + response.body();
            }
        } catch (HttpTimeoutException e) {
            return "Error: La solicitud al servidor agotó el tiempo de espera. Detalles: " + e.getMessage();
        } catch (IOException e) {
            return "Error de entrada/salida al enviar la solicitud: " + e.getMessage();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Restaurar estado de interrupción
            return "Error: La operación fue interrumpida. Detalles: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }
}
