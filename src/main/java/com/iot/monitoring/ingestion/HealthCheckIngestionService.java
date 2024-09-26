package com.iot.monitoring.ingestion;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckIngestionService {
    
    private final KafkaTemplate<String, String> kafkaTemplate;

    public HealthCheckIngestionService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendHealthCheck(String healthStatus) {
        kafkaTemplate.send("health", healthStatus);
    }
}
