package com.iot.monitoring.repository;

import com.iot.monitoring.model.DeviceMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceMetadataRepository extends JpaRepository<DeviceMetadata, String> {
    // Additional query methods can be defined here
}
