package com.medisupply.ingresobodega.config;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

public class ConfigDynamo {
    @Value("${aws.region}") private String region;
    @Value("${aws.accessKey}") private String accessKey;
    @Value("${aws.secretKey}") private String secretKey;

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

}
