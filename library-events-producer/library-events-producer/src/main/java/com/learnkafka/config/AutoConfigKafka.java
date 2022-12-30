package com.learnkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AutoConfigKafka {

    //auto create topic in kafka
    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("library-events")
                .partitions(2)
                .replicas(1)
                .build();
    }
}
