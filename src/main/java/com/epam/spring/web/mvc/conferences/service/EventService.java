package com.epam.spring.web.mvc.conferences.service;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;

import java.util.List;

public interface EventService {
  EventsDTO getEvent(int id);

  EventsDTO createEvent(EventsDTO eventsDTO);

  EventsDTO updateEvent(int id, EventsDTO eventsDTO);

  void deleteEvent(int id);

  int calculateEventsNumber();

  List<EventsDTO> getEventByPlaceUA(String event_place_ua);

  List<EventsDTO> getEventByPlaceEN(String event_place_en);
}
