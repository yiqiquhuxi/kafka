package org.example.consumer.listener;

import com.alibaba.fastjson.JSON;
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
    public void onMessage(String msg) {
        try {
            MessagePayload payload = JSON.parseObject(msg, MessagePayload.class);
            log.info("✅ received: id={}, content={}, ts={}",
                    payload.id(), payload.content(), payload.ts());
        } catch (Exception e) {
            log.error("❌ JSON解析失败，原始消息: {}", msg, e);
        }
    }
}
