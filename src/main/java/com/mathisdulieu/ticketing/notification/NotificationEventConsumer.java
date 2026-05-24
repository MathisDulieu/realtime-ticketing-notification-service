package com.mathisdulieu.ticketing.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationEventConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "#{@kafkaProvider.getTopics()}", groupId = "#{@kafkaProvider.getGroupId()}")
    public void consume(final NotificationEvent notificationEvent, final Acknowledgment acknowledgment) {
        log.debug("Received Kafka event: eventId={}", notificationEvent.eventId());

        notificationService.doSomething(notificationEvent);

        acknowledgment.acknowledge();
    }
}
