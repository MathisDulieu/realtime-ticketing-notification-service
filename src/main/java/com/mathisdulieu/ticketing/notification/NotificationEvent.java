package com.mathisdulieu.ticketing.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record NotificationEvent(
        String eventId
) {
}
