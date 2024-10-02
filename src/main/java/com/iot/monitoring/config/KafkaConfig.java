package com.iot.monitoring.config;

import com.iot.monitoring.dto.VehicleTrackingDTO;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@EnableKafkaStreams
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public static final String TELEMATICS_EVENTS = "telematics-events";
    public static final String TELEMATICS_EVENTS_OUTPUT = "telematics-events-output";

    private static final String CONSUMER_GROUP_ID = "iot-group";
    private static final String TRUSTED_PACKAGES = "com.iot.monitoring.dto";
    private static final long POLL_TIMEOUT = 3000;

    @Bean
    ProducerFactory<String, VehicleTrackingDTO> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    KafkaTemplate<String, VehicleTrackingDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    ConsumerFactory<String, VehicleTrackingDTO> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_ID);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, TRUSTED_PACKAGES);
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, VehicleTrackingDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, VehicleTrackingDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setPollTimeout(POLL_TIMEOUT);
        return factory;
    }

    @Bean
    NewTopic telematicsTopic() {
        return TopicBuilder.name(TELEMATICS_EVENTS)
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic telematicsOutputTopic() {
        return TopicBuilder.name(TELEMATICS_EVENTS_OUTPUT)
                .partitions(2)
                .replicas(1)
                .build();
    }
}