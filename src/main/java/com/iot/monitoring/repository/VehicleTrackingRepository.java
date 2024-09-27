package com.iot.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iot.monitoring.model.VehicleTracking;

public interface VehicleTrackingRepository extends JpaRepository<VehicleTracking, Long> {

}
