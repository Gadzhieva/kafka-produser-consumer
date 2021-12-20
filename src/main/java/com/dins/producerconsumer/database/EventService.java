package com.dins.producerconsumer.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;

    @Transactional
    public void saveAll(List<Event> events) {
        repository.saveAll(events);
    }

    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return repository.findAll();
    }

}
