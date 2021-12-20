package com.dins.producerconsumer.kafka.producer;

import com.dins.producerconsumer.database.Event;
import com.dins.producerconsumer.database.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    @Value(value = "${topic.name}")
    private String topicName;

    private final EventService eventService;

    private final KafkaTemplate<String, Event> kafkaTemplate;

    public void sendMessage(Event event) {
        kafkaTemplate.send(topicName, event);
        log.info("Event: " + event + " sent to topic " + topicName);
    }

    public void send() {
        List<Event> events = eventService.findAll();
        events.forEach(this::sendMessage);
        log.info(events.size() + " events sent");
    }
}
