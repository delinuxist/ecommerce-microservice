package com.example.notificationservice.consumer;

import com.example.notificationservice.dto.EventData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(EventData eventData) {
        log.info("Received Notification for Order - {}",eventData.getOrderNumber());
    }
}
