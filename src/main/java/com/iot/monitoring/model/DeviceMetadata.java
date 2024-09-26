package com.iot.monitoring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DeviceMetadata {
    @Id
    private String deviceId;
    private String deviceName;
    private String deviceType;
}
