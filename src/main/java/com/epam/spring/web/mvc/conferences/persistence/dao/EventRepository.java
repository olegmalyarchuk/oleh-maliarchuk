package com.epam.spring.web.mvc.conferences.persistence.dao;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import com.epam.spring.web.mvc.conferences.persistence.model.User;

import java.util.List;

public interface EventRepository {
    Events getEvent(int id);

    Events createEvent(Events events);

    Events updateEvent(int id, Events events);

    void deleteEvent(int id);

    int calculateEventsNumber();

    List<Events> getEventByPlaceUA(String event_place_ua);

    List<Events> getEventByPlaceEN(String event_place_en);
}
