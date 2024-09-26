package com.iot.monitoring.repository;

import com.iot.monitoring.model.HealthCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthCheckRepository extends JpaRepository<HealthCheck, Long> {
    // Additional query methods can be defined here
}
