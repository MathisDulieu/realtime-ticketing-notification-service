package com.mathisdulieu.ticketing.notification.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KafkaProviderTest {

    @Mock
    private KafkaProperties kafkaProperties;
    @InjectMocks
    private KafkaProvider kafkaProvider;

    @Test
    void shouldGetTopicsFromProperties() {
        // Arrange
        when(kafkaProperties.topics()).thenReturn(List.of("topic1", "topic2"));

        // Act
        List<String> topics = kafkaProvider.getTopics();

        // Assert
        assertThat(topics).isEqualTo(List.of("topic1", "topic2"));
    }

    @Test
    void shouldGetGroupIdFromProperties() {
        // Arrange
        when(kafkaProperties.groupId()).thenReturn("groupId");

        // Act
        String groupId = kafkaProvider.getGroupId();

        // Assert
        assertThat(groupId).isEqualTo("groupId");
    }

}
