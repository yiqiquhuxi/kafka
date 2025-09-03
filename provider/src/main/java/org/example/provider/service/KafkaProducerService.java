package org.example.provider.service;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.example.provider.kafka.MessagePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafka;

    @Value("${app.kafka.topic}")
    private String topic;


    public void send(String content) {
        MessagePayload payload = new MessagePayload(
                UUID.randomUUID().toString(),
                content,
                System.currentTimeMillis()
        );
        String jsonStr = JSONUtil.toJsonStr(payload);
        kafka.send(topic, jsonStr);
    }
}
