package org.example.provider.kafka;

public record MessagePayload(String id, String content, long ts) { }

