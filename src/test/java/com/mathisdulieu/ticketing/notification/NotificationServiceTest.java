package com.mathisdulieu.ticketing.notification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void shouldNotThrow_whenDoSomething() {
        // Arrange
        NotificationEvent notificationEvent = NotificationEvent.builder()
                .eventId("event-id")
                .build();

        // Act & Assert
        assertThatCode(() -> notificationService.doSomething(notificationEvent))
                .doesNotThrowAnyException();
    }

}
