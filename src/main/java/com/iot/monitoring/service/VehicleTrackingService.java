package com.iot.monitoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.monitoring.model.VehicleTracking;
import com.iot.monitoring.repository.VehicleTrackingRepository;

@Service
public class VehicleTrackingService {

	@Autowired
	private VehicleTrackingRepository repository;

	public void save(VehicleTracking tracking) {
		repository.save(tracking);
	}

}
