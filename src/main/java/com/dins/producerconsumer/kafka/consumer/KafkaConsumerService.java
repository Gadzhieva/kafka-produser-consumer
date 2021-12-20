package com.dins.producerconsumer.kafka.consumer;

import com.dins.producerconsumer.database.Event;
import com.dins.producerconsumer.database.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    @Value(value = "${topic.name}")
    private String topicName;

    private final EventService eventService;
    private final ConsumerFactory<String, Event> consumerFactory;

    public void receive() {
        Consumer<String, Event> consumer = consumerFactory.createConsumer();
        TopicPartition topicPartition = new TopicPartition(topicName, 0);
        List<TopicPartition> topicPartitions = List.of(topicPartition);
        consumer.assign(topicPartitions);
        consumer.seekToBeginning(topicPartitions);

        while (true) {
            ConsumerRecords<String, Event> records = consumer.poll(Duration.ofSeconds(1));
            if (!records.isEmpty()) {
                List<Event> entities = records.records(topicPartition).stream()
                        .map(ConsumerRecord::value)
                        .peek(event -> log.info("Event created: " + event))
                        .toList();
                eventService.saveAll(entities);
                log.info(entities.size() + " events created");
            } else {
                consumer.unsubscribe();
                break;
            }
        }
    }

}
