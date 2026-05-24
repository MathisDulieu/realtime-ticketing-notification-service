package com.mathisdulieu.ticketing.notification;

import com.mathisdulieu.ticketing.library.test.kafka.config.KafkaConsumerTestConfig;
import com.mathisdulieu.ticketing.library.test.kafka.config.KafkaProducerTestConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class RealtimeTicketingNotificationServiceConfigurationTests {

    @Bean
    public MBeanExporter exporter() {
        return mock(MBeanExporter.class);
    }

    @Bean
    @ConditionalOnProperty("spring.embedded.kafka.brokers")
    public KafkaTemplate<String, NotificationEvent> kafkaTemplate(Environment environment) {
        return KafkaProducerTestConfig.kafkaTemplate(environment.getProperty("spring.kafka.bootstrap-servers"));
    }

    @Bean
    @ConditionalOnProperty("spring.embedded.kafka.brokers")
    public ConcurrentKafkaListenerContainerFactory<String, NotificationEvent> notificationEventKafkaListenerContainerFactory(Environment environment) {
        ConsumerFactory<String, NotificationEvent> consumerFactory = KafkaConsumerTestConfig.consumerFactory(environment.getProperty("spring.embedded.kafka.brokers"), NotificationEvent.class);
        return KafkaConsumerTestConfig.listenerContainerFactory(consumerFactory);
    }
}
