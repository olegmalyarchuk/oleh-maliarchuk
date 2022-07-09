package com.epam.spring.web.mvc.conferences.service.impl;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.mapper.EventListMapper;
import com.epam.spring.web.mvc.conferences.mapper.EventMapper;
import com.epam.spring.web.mvc.conferences.mapper.UserListMapper;
import com.epam.spring.web.mvc.conferences.mapper.UserMapper;
import com.epam.spring.web.mvc.conferences.persistence.dao.EventRepository;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import com.epam.spring.web.mvc.conferences.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Autowired
    EventMapper mapper;

    @Autowired
    EventListMapper mapperList;


    @Override
    public EventsDTO getEvent(int id) {
        log.info("EventService: get event by id {}", id);
        return mapper.toDTO(eventRepository.getEvent(id));
    }

    @Override
    public EventsDTO createEvent(EventsDTO eventsDTO) {
        Events events = mapper.toModel(eventsDTO);
        events = eventRepository.createEvent(events);
        log.info("EventService: create event {}", eventsDTO);
        return mapper.toDTO(events);
    }

    @Override
    public EventsDTO updateEvent(int id, EventsDTO eventsDTO) {
        Events events = mapper.toModel(eventsDTO);
        events = eventRepository.updateEvent(id, events);
        log.info("EventService: update event with id {}", id);
        return mapper.toDTO(events);
    }

    @Override
    public void deleteEvent(int id) {
        eventRepository.deleteEvent(id);
        log.info("EventService: delete event with id {}", id);
    }

    @Override
    public int calculateEventsNumber() {
        log.info("EventService: events count{}", eventRepository.calculateEventsNumber());
        return eventRepository.calculateEventsNumber();
    }

    @Override
    public List<EventsDTO> getEventByPlaceUA(String event_place_ua) {
        log.info("Got Events by place_ua: " + event_place_ua);
        List<Events> eventsList = eventRepository.getEventByPlaceUA(event_place_ua);
        return mapperList.toDTOList(eventsList);
    }

    @Override
    public List<EventsDTO> getEventByPlaceEN(String event_place_en) {
        log.info("Got Events by place_en: " + event_place_en);
        List<Events> eventsList = eventRepository.getEventByPlaceUA(event_place_en);
        return mapperList.toDTOList(eventsList);
    }

}
