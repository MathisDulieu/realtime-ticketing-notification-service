package com.mathisdulieu.ticketing.notification;

import com.mathisdulieu.ticketing.library.test.kafka.consumer.GenericTestKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"test-topic-1"})
@ActiveProfiles("test")
@Import({
    RealtimeTicketingNotificationServiceConfigurationTests.class,
    RealtimeTicketingNotificationServiceIntegrationTest.TestKafkaConsumer.class
})
public class RealtimeTicketingNotificationServiceIntegrationTest {

    @Autowired
    private KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    @Autowired
    private TestKafkaConsumer testKafkaConsumer;

    @Test
    void shouldDoSomething_whenNotificationEventIsReceived() throws InterruptedException {
        // Arrange
        NotificationEvent notificationEvent = NotificationEvent.builder()
            .eventId("event-id")
            .build();
        
        // Act
        kafkaTemplate.send("test-topic-1", "key", notificationEvent);

        // Assert
        ConsumerRecord<String, NotificationEvent> record = testKafkaConsumer.consumer.pollRecord("test-topic-1", 5);
        assertThat(record.value()).isEqualTo(notificationEvent);
        assertThat(record.topic()).isEqualTo("test-topic-1");
        assertThat(record.key()).isEqualTo("key");
    }

    @TestComponent
    static class TestKafkaConsumer {
        final GenericTestKafkaConsumer<NotificationEvent> consumer = new GenericTestKafkaConsumer<>();

        @KafkaListener(
            topics = {"test-topic-1"},
            groupId = "test-group",
            containerFactory = "notificationEventKafkaListenerContainerFactory"
        )
        public void consume(ConsumerRecord<String, NotificationEvent> record) {
            consumer.records.add(record);
        }
    }
}
