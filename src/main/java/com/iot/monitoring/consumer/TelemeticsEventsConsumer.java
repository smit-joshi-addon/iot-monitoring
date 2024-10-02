package com.iot.monitoring.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.iot.monitoring.dto.VehicleTrackingDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TelemeticsEventsConsumer {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@KafkaListener(topics = "telematics-events-output", groupId = "iot-group")
	public void consume(VehicleTrackingDTO message) {
		log.info("event received: {}", message);

		sendToSocket(message);
	}

	private void sendToSocket(VehicleTrackingDTO trackingDTO) {
		messagingTemplate.convertAndSend("/topic/messages", trackingDTO);
	}

}
