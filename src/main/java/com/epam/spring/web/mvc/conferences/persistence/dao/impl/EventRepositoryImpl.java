package com.epam.spring.web.mvc.conferences.persistence.dao.impl;

import com.epam.spring.web.mvc.conferences.exception.EventNotFoundException;
import com.epam.spring.web.mvc.conferences.persistence.dao.EventRepository;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EventRepositoryImpl implements EventRepository {
    private final List<Events> list = new ArrayList<>();

    @Override
    public Events getEvent(int id) {
        log.info("Got Event by id: " + id);
        return list.stream().filter(events -> events.getEvent_id()==id)
                .findFirst().orElseThrow(EventNotFoundException::new);
    }

    @Override
    public Events createEvent(Events events) {
        list.add(events);
        log.info("Created Event " + events);
        return events;
    }

    @Override
    public Events updateEvent(int id, Events events) {
        boolean isUpdated = list.removeIf(e -> e.getEvent_id()==id);
        if (isUpdated) {
            list.add(events);
        } else {
            throw new EventNotFoundException();
        }
        log.info("Event with id " + id + " was updated");
        return events;
    }

    @Override
    public void deleteEvent(int id) {
        list.removeIf(events -> events.getEvent_id()==id);
        log.info("Event with id {} was deleted", id);
    }

    @Override
    public int calculateEventsNumber() {
        log.info("Events number{}", list.size());
        return list.size();
    }

    @Override
    public List<Events> getEventByPlaceUA(String event_place_ua) {
        log.info("Got Event by place_ua: " + event_place_ua);
        return list.stream().filter(events -> events.getEvent_place_ua().equals(event_place_ua)).collect(Collectors.toList());
    }

    @Override
    public List<Events> getEventByPlaceEN(String event_place_en) {
        log.info("Got Event by place_en: " + event_place_en);
        return list.stream().filter(events -> events.getEvent_place_en().equals(event_place_en)).collect(Collectors.toList());

    }

}
