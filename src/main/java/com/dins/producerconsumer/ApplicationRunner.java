package com.dins.producerconsumer;

import com.dins.producerconsumer.kafka.consumer.KafkaConsumerService;
import com.dins.producerconsumer.kafka.producer.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    @Value(value = "${application.mode}")
    private ApplicationMode mode;

    private final KafkaProducerService producerService;

    private final KafkaConsumerService consumerService;

    @Override
    public void run(String... args) {
        switch (mode) {
            case PRODUCER -> producerService.send();
            case CONSUMER -> consumerService.receive();
        }
    }
}
