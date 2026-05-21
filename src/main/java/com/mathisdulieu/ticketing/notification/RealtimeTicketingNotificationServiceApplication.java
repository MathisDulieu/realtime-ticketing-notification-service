package com.mathisdulieu.ticketing.notification;

import com.mathisdulieu.ticketing.notification.config.KafkaProperties;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Date;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.mathisdulieu.ticketing.notification"})
@EnableConfigurationProperties({
        KafkaProperties.class
})
public class RealtimeTicketingNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealtimeTicketingNotificationServiceApplication.class, args);
    }

    @PostConstruct
    void steUtcTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        log.info("RealtimeTicketingNotificationServiceApplication running in UTC timezone at : {}", new Date());
    }

    @Configuration
    @Profile("test")
    @ComponentScan(lazyInit = true)
    static class ConfigForShorterBootTimeForTests {
    }
}