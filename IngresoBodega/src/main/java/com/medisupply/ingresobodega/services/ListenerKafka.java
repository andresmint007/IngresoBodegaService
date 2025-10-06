package com.medisupply.ingresobodega.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medisupply.ingresobodega.entities.MovimientoInventario;
import com.medisupply.ingresobodega.repository.DynamoDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ListenerKafka {
    @Autowired
    DynamoDbRepository repo;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "Income", groupId = "group2")
    public void procesarEvento(String mensajeJson) throws Exception {
        try {
            JsonNode json = objectMapper.readTree(mensajeJson);
            String productoID = json.get("productoID").asText();
            int cantidad = json.get("stock").asInt();

            if (repo.productoExiste(productoID)) {
                MovimientoInventario mov = new MovimientoInventario();
                mov.setMovimientoInventarioID(UUID.randomUUID().toString());
                mov.setProductoID(productoID);
                mov.setTipoMovimiento("Ingreso");
                mov.setCantidad(cantidad);
                mov.setFechaMovimiento(new Date().toString());
                repo.guardarMovimiento(mov);

                List<MovimientoInventario> ingresos = repo.buscarMovimientosIngreso(productoID);
                int nuevoStock = ingresos.stream().mapToInt(MovimientoInventario::getCantidad).sum();

                String eventoSalida = "{ \"productoID\": \"" + productoID + "\", \"stock\": " + nuevoStock + " }";
                kafkaTemplate.send("UpdateStock", eventoSalida);
            }else{
                System.out.println("Producto no encontrado, nombre: "  + productoID);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
