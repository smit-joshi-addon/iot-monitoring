package com.iot.monitoring.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.iot.monitoring.dto.VehicleTrackingDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TelemeticsEventsConsumer {

	@KafkaListener(topics = "telematics-events-output", groupId = "iot-group")
	public void consume(VehicleTrackingDTO message) {
		log.info("event received: {}", message);
	}

}
