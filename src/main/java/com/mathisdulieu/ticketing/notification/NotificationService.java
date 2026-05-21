package com.mathisdulieu.ticketing.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationService {

    public void doSomething(NotificationEvent notificationEvent) {
        log.info("I did something with eventId: {} ", notificationEvent.eventId());
    }

}
