package org.example.provider.conf;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicsConfig {

    @Value("${app.kafka.topic}")
    private String topic;

    // 只有当 app.kafka.auto-create-topic=true（或缺省并 matchIfMissing=true）才注册 NewTopic
    @Bean
    @ConditionalOnProperty(name = "app.kafka.auto-create-topic", havingValue = "true", matchIfMissing = true)
    public NewTopic demoTopic() {
        // 分区/副本按你的集群实际调整；单 Broker 可用 (3,1)
        return new NewTopic(topic, 3, (short) 1);
    }
}
