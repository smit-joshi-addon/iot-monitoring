package com.iot.monitoring.processing;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

import com.iot.monitoring.config.KafkaConfig;
import com.iot.monitoring.dto.VehicleTrackingDTO;
import com.iot.monitoring.model.VehicleTracking.EventType;

@EnableKafkaStreams
@Service
public class TelemeticsEventsProcessingService {

	private long processedEventCount = 0;

	public TelemeticsEventsProcessingService(StreamsBuilder streamsBuilder) {
		KStream<String, VehicleTrackingDTO> eventStream = streamsBuilder.stream(KafkaConfig.TELEMATICS_EVENTS,
				Consumed.with(Serdes.String(), new JsonSerde<>(VehicleTrackingDTO.class)));

		// Do Some Processing, ex. filter the events by the EventType.NORMAL
		KStream<String, VehicleTrackingDTO> normalEvents = eventStream
				.filter((key, value) -> value.eventType().equals(EventType.NORMAL));

		normalEvents.foreach((key, value) -> {
			processedEventCount++;
		});

		normalEvents.to(KafkaConfig.TELEMATICS_EVENTS_OUTPUT,
				Produced.with(Serdes.String(), new JsonSerde<>(VehicleTrackingDTO.class)));
	}

	public long getProcessedEventCount() {
		return processedEventCount;
	}

}
