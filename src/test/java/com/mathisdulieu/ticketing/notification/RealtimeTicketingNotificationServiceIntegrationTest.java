package com.mathisdulieu.ticketing.notification;

import com.mathisdulieu.ticketing.notification.utils.ProducerRecordBuilder;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(partitions = 1)
@ActiveProfiles("test")
@Import(RealtimeTicketingNotificationServiceConfigurationTests.class)
public class RealtimeTicketingNotificationServiceIntegrationTest {

    @Autowired
    private KafkaTemplate<String, NotificationEvent> kafkaTemplate;
    @Autowired
    private NotificationEventConsumer notificationEventConsumer;

    @Test
    void shouldDoSomething_whenNotificationEventIsReceived() {
        // Arrange
        NotificationEvent notificationEvent = NotificationEvent.builder()
                .eventId("event-id")
                .build();

        ProducerRecord<String, NotificationEvent> record = ProducerRecordBuilder.record(String.class, NotificationEvent.class)
                .topic("test-topic-1")
                .key("key")
                .value(notificationEvent)
                .build();

        // Act
        kafkaTemplate.send(record);

        // Assert
        assertThat(true).isTrue();
    }

}
