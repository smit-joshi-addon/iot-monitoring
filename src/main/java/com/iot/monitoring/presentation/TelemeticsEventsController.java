package com.iot.monitoring.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.monitoring.model.VehicleTracking;
import com.iot.monitoring.producer.TelemeticsEventsProducerService;

@RestController
@RequestMapping("/api/v1")
public class TelemeticsEventsController {

	@Autowired
	private TelemeticsEventsProducerService service;

	@PostMapping("/event")
	public void send(@RequestBody VehicleTracking trackingData) {
		service.produceEvent(trackingData);
	}
}
