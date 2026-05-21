package com.mathisdulieu.ticketing.notification.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaProvider {

    private final KafkaProperties kafkaProperties;

    public List<String> getTopics() {
        return kafkaProperties.topics();
    }

    public String getGroupId() {
        return kafkaProperties.groupId();
    }
}
