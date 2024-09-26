package com.iot.monitoring.processing;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Service;

@EnableKafkaStreams
@Service
public class HealthProcessingService {

	private final String HEALTH = "health";
	private final String HEALTH_OUTPUT = "health-output";

	public HealthProcessingService(StreamsBuilder streamsBuilder) {
		KStream<String, String> healthStream = streamsBuilder.stream(HEALTH,
				Consumed.with(Serdes.String(), Serdes.String()));

		KStream<String, String> modifiedHealthStream = healthStream
				.mapValues((readOnlyKey, value) -> value.toUpperCase());

		modifiedHealthStream.print(Printed.<String, String>toSysOut().withLabel("modifiedHealthStream"));

		modifiedHealthStream.to(HEALTH_OUTPUT, Produced.with(Serdes.String(), Serdes.String()));
	}

}
