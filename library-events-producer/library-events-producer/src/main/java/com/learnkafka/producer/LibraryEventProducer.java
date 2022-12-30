package com.learnkafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.domain.LibraryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class LibraryEventProducer {

    private static final String KAFKA_TOPIC = "library-events";

    @Autowired
    KafkaTemplate<Integer,String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    //This  method will send data to kafka in synchronous way
    public void sendLibraryEventSync(LibraryEvent libraryEvent) throws JsonProcessingException {
        Integer key = libraryEvent.getLibraryEventId();
        String value = objectMapper.writeValueAsString(libraryEvent);
        SendResult<Integer,String> sendResult = null;
        try {
            sendResult=    kafkaTemplate.send(KAFKA_TOPIC,key,value).get();
            System.out.println(sendResult.getProducerRecord().toString());
            log.info("Send data successfully to kafka Data - Key : {} Value : {}",key,value );
        } catch (Exception e) {
            log.error("Error sending message to kafka {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.info("Printing After try catch method");
    }

    public void sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
        Integer key = libraryEvent.getLibraryEventId();
        String value = objectMapper.writeValueAsString(libraryEvent);
        //Sending data in Async Mode
        ListenableFuture<SendResult<Integer,String>> listenableFuture =  kafkaTemplate.send(KAFKA_TOPIC,key,value);
        listenableFuture.addCallback(new SuccessCallback<SendResult<Integer, String>>() {
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccess(key,value,result);
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key,value,ex);
            }
        });
        System.out.println("Printing data after sending");
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }

    private void handleFailure(Integer key, String value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }


    }

}
