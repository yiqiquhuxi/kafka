package org.example.consumer.kafka;


// 与 provider 相同的消息结构（也可抽成 common 模块依赖）
public record MessagePayload(String id, String content, long ts) { }
