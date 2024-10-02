package com.iot.monitoring.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.iot.monitoring.config.KafkaConfig;
import com.iot.monitoring.dto.VehicleTrackingDTO;
import com.iot.monitoring.model.VehicleTracking;
import com.iot.monitoring.service.VehicleTrackingService;

@Service
public class TelemeticsEventsProducerService {

	private final KafkaTemplate<String, VehicleTrackingDTO> kafkaTemplate;
	private final VehicleTrackingService vehicleTrackingService;

	public TelemeticsEventsProducerService(KafkaTemplate<String, VehicleTrackingDTO> kafkaTemplate,
			VehicleTrackingService vehicleTrackingService) {
		this.kafkaTemplate = kafkaTemplate;
		this.vehicleTrackingService = vehicleTrackingService;

	}

	public void produceEvent(VehicleTracking trackingData) {
		kafkaTemplate.send(KafkaConfig.TELEMATICS_EVENTS, trackingData.getDeviceId().toString(),
				VehicleTrackingDTO.mapToVehicleTrackingDTO(trackingData));
		vehicleTrackingService.save(trackingData);
	}
}
