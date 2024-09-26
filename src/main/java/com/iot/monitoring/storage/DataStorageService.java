package com.iot.monitoring.storage;

import org.springframework.stereotype.Service;

import com.iot.monitoring.model.HealthCheck;
import com.iot.monitoring.repository.HealthCheckRepository;

@Service
public class DataStorageService {
    
    private final HealthCheckRepository healthCheckRepository;

    public DataStorageService(HealthCheckRepository healthCheckRepository) {
        this.healthCheckRepository = healthCheckRepository;
    }

    public void saveHealthCheck(HealthCheck healthCheck) {
        healthCheckRepository.save(healthCheck);
    }
}
