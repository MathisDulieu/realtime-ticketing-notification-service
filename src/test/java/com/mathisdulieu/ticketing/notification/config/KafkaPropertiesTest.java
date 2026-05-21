package com.mathisdulieu.ticketing.notification.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KafkaPropertiesTest {

    @Test
    void shouldNotThrow_whenPropertiesAreComplete() {
        // Arrange
        KafkaProperties kafkaProperties = new KafkaProperties(List.of("topic1", "topic2"), "groupId");

        // Act
        kafkaProperties.afterPropertiesSet();

        // Assert
        assertThat(kafkaProperties.topics()).isEqualTo(List.of("topic1", "topic2"));
        assertThat(kafkaProperties.groupId()).isEqualTo("groupId");
    }

    @ParameterizedTest
    @MethodSource("getCasesWithIncompleteProperties")
    void shouldThrow_whenPropertiesAreIncomplete(List<String> topics, String groupId, String errorMessage) {
        // Arrange
        KafkaProperties kafkaProperties = new KafkaProperties(topics, groupId);

        // Act & Assert
        assertThatThrownBy(kafkaProperties::afterPropertiesSet)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    private static Stream<Arguments> getCasesWithIncompleteProperties() {
        return Stream.of(
                Arguments.of(topicsList("topic1", "topic2"), " ", "notification.kafka.groupId must be given"),
                Arguments.of(topicsList("topic1"), null, "notification.kafka.groupId must be given"),
                Arguments.of(emptyList(), "groupId", "notification.kafka.topics must not be null or empty"),
                Arguments.of(null, "groupId", "notification.kafka.topics must not be null or empty"),
                Arguments.of(topicsList("topic1", null), "groupId", "Each topic in notification.kafka.topics must be a non-blank string"),
                Arguments.of(topicsList("topic1", " "), "groupId", "Each topic in notification.kafka.topics must be a non-blank string")
        );
    }

    private static List<String> topicsList(String... values) {
        return new ArrayList<>(Arrays.asList(values));
    }
}