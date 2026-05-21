package com.mathisdulieu.ticketing.notification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.support.Acknowledgment;

@ExtendWith(MockitoExtension.class)
public class NotificationEventConsumerTest {

    @Mock
    private NotificationService notificationService;
    @Mock
    private Acknowledgment acknowledgment;
    @InjectMocks
    private NotificationEventConsumer notificationEventConsumer;

    @Test
    void shouldConsumeAndProcessNotificationEvent() {
        // Arrange
        NotificationEvent notificationEvent = NotificationEvent.builder()
                .eventId("event-id")
                .build();

        // Act
        notificationEventConsumer.consume(notificationEvent, acknowledgment);

        // Assert
        InOrder inOrder = Mockito.inOrder(notificationService, acknowledgment);
        inOrder.verify(notificationService).doSomething(notificationEvent);
        inOrder.verify(acknowledgment).acknowledge();
        inOrder.verifyNoMoreInteractions();
    }

}
