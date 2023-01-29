package com.learnkafka.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingConsumerAwareMessageListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibraryEventsConsumerManualOffset implements AcknowledgingMessageListener<Integer,String> {

    private static final String KAFKA_TOPIC = "library-events";

    public void onMessage(ConsumerRecord<Integer,String> consumerRecord){
        log.info("Consumer Record {}", consumerRecord.value());
    }

    @KafkaListener(topics = {KAFKA_TOPIC})
    @Override
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord, Acknowledgment acknowledgment) {
        log.info("Consumer Record {}", consumerRecord.value());
        acknowledgment.acknowledge();
    }
}
