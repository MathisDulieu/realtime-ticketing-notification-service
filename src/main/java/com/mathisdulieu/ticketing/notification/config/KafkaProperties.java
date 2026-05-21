package com.mathisdulieu.ticketing.notification.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notEmpty;

@ConfigurationProperties(prefix = "notification.kafka")
public record KafkaProperties(
        List<String> topics,
        String groupId
) implements InitializingBean {
    @Override
    public void afterPropertiesSet() {
        hasText(groupId, "notification.kafka.groupId must be given");
        notEmpty(topics, "notification.kafka.topics must not be null or empty");
        topics.forEach(topic -> hasText(topic, "Each topic in notification.kafka.topics must be a non-blank string"));
    }
}
