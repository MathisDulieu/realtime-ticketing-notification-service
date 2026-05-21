package com.mathisdulieu.ticketing.notification;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class RealtimeTicketingNotificationServiceConfigurationTests {

    @Autowired
    private Environment environment;

    @Bean
    public MBeanExporter exporter() {
        return mock(MBeanExporter.class);
    }

    @Bean
    public ProducerFactory<String, NotificationEvent> producerFactory() {
        String bootstrapServers = environment.getProperty("spring.kafka.bootstrap-servers");
        return new DefaultKafkaProducerFactory<>(Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
        ));
    }

    @Bean
    public KafkaTemplate<String, NotificationEvent> kafkaTemplate(ProducerFactory<String, NotificationEvent> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
