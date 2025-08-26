package org.example.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.example.consumer.kafka.MessagePayload;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumerListener {


    @KafkaListener(
            topics = "${app.kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void onMessage(@Payload MessagePayload msg) {
        log.info("✅ received: id=" + msg.id()
                + ", content=" + msg.content()
                + ", ts=" + msg.ts());
    }
}
