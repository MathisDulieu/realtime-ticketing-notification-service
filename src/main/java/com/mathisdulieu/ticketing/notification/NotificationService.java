package com.mathisdulieu.ticketing.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    public void doSomething(final NotificationEvent notificationEvent) {
        log.info("I did something with eventId: {} ", notificationEvent.eventId());
    }

}
