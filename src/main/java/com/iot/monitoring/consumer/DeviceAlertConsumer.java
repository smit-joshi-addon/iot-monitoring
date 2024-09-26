package com.iot.monitoring.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DeviceAlertConsumer {

	@KafkaListener(topics = "health-output", groupId = "iot-group")
	public void consume(String message) {
		// Handle the incoming message (e.g., log it or save it to the database)
		System.out.println("Received alert: " + message);
	}
}
