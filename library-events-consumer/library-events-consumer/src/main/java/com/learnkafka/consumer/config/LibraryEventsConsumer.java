package com.learnkafka.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibraryEventsConsumer {

    private static final String KAFKA_TOPIC = "library-events";
    @KafkaListener(topics = {KAFKA_TOPIC})
    public void onMessage(ConsumerRecord<Integer,String> consumerRecord){
        log.info("Consumer Record {}", consumerRecord.value());
    }
}
