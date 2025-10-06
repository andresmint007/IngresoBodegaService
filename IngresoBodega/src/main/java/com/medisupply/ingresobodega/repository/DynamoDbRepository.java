package com.medisupply.ingresobodega.repository;
import com.medisupply.ingresobodega.entities.MovimientoInventario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

@Repository
public class DynamoDbRepository {

    private final DynamoDbClient dynamoDb;
    @Value("${dynamodb.table.productos}") private String productosTable;
    @Value("${dynamodb.table.movimientos}") private String movimientosTable;

    public DynamoDbRepository(DynamoDbClient dynamoDb) {
        this.dynamoDb = dynamoDb;
    }
    public boolean productoExiste(String productoID) {
        try {
            Map<String, AttributeValue> key = Map.of(
                    "products", AttributeValue.builder().s(productoID).build()
            );
            GetItemRequest request = GetItemRequest.builder()
                    .tableName("products_medisupply")
                    .key(key)
                    .build();
            GetItemResponse resp = dynamoDb.getItem(request);
            return resp.hasItem();
        } catch (AwsServiceException e) {
            throw new RuntimeException(e);
        } catch (SdkClientException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarMovimiento(MovimientoInventario mov) {
        try {
            Map<String, AttributeValue> item = new HashMap<>();
            // Clave principal según la definición de la tabla:
            item.put("inventory", AttributeValue.builder().s(mov.getMovimientoInventarioID()).build());
            item.put("tipoMovimiento", AttributeValue.builder().s(mov.getTipoMovimiento()).build());
            item.put("cantidad", AttributeValue.builder().n(String.valueOf(mov.getCantidad())).build());
            item.put("fechaMovimiento", AttributeValue.builder().s(mov.getFechaMovimiento()).build());
            if (mov.getProductoID() != null)
                item.put("productoID", AttributeValue.builder().s(mov.getProductoID()).build());
            PutItemRequest req = PutItemRequest.builder().tableName(movimientosTable).item(item).build();
            dynamoDb.putItem(req);
        } catch (AwsServiceException e) {
            throw new RuntimeException(e);
        } catch (SdkClientException e) {
            throw new RuntimeException(e);
        }
    }


    public List<MovimientoInventario> buscarMovimientosIngreso(String productoID) {
        // Para producción, usa GSI; aquí simple scan por demo
        try {
            ScanRequest req = ScanRequest.builder()
                    .tableName(movimientosTable)
                    .filterExpression("productoID = :pid and tipoMovimiento = :tm")
                    .expressionAttributeValues(Map.of(
                            ":pid", AttributeValue.builder().s(productoID).build(),
                            ":tm", AttributeValue.builder().s("Ingreso").build()
                    )).build();
            ScanResponse resp = dynamoDb.scan(req);
            List<MovimientoInventario> movimientos = new ArrayList<>();
            for (Map<String, AttributeValue> item : resp.items()) {
                MovimientoInventario mi = new MovimientoInventario();
                mi.setCantidad(Integer.parseInt(item.get("cantidad").n()));
                movimientos.add(mi);
            }
            return movimientos;
        } catch (AwsServiceException e) {
            throw new RuntimeException(e);
        } catch (SdkClientException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
