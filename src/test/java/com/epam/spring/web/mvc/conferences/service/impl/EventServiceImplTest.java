package com.epam.spring.web.mvc.conferences.service.impl;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.exception.EventNotFoundException;
import com.epam.spring.web.mvc.conferences.exception.ReportNotFoundException;
import com.epam.spring.web.mvc.conferences.persistence.dao.EventRepository;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {

  private static final int MOCK_ID = 1;
  private static final int MOCK_UPDATED_ID = 2;
  private static final String MOCK_LOCATION_EN = "Lviv";
  private static final String MOCK_UPDATED_LOCATION_EN = "Kyib";
  private static final String MOCK_LOCATION_UA = "Львів";

  @InjectMocks private EventServiceImpl eventService;
  @Mock private EventRepository eventRepository;

  @Test
  void getEventByIdTest() {
    // given
    Events expectedEvent = Events.builder().eventId(MOCK_ID).build();
    when(eventRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedEvent));

    // when
    EventsDTO actualEvent = eventService.getEvent(MOCK_ID);

    // then
    assertEquals(expectedEvent.getEventId(), actualEvent.getEventId());
  }

  @Test
  void getEventByPlaceUATEST() {
    Events event = Events.builder().eventPlaceUa(MOCK_LOCATION_UA).build();
    List<Events> eventsList = new ArrayList<>();
    eventsList.add(event);
    when(eventRepository.findAllByEventPlaceUa(MOCK_LOCATION_UA)).thenReturn(eventsList);

    // when
    List<EventsDTO> actualEvents = eventService.getEventByPlaceUA(MOCK_LOCATION_UA);

    // then
    assertEquals(actualEvents.size(), eventsList.size());
  }

  @Test
  void getEventByPlaceENTEST() {
    Events event = Events.builder().eventPlaceEn(MOCK_LOCATION_EN).build();
    List<Events> eventsList = new ArrayList<>();
    eventsList.add(event);
    when(eventRepository.findAllByEventPlaceEn(MOCK_LOCATION_EN)).thenReturn(eventsList);

    // when
    List<EventsDTO> actualEvents = eventService.getEventByPlaceEN(MOCK_LOCATION_EN);

    // then
    assertEquals(actualEvents.size(), eventsList.size());
  }

  @Test
  void updateEventTest() {
    // given
    Events event = Events.builder().eventId(1).eventPlaceEn(MOCK_LOCATION_EN).build();
    EventsDTO updatedEvent =
        EventsDTO.builder().eventId(1).eventPlaceEn(MOCK_UPDATED_LOCATION_EN).build();
    when(eventRepository.findById(1)).thenReturn(Optional.of(event));
    when(eventRepository.save(any())).thenReturn(event);

    // when
    updatedEvent = eventService.updateEvent(MOCK_ID, updatedEvent);

    // then
    assertThat(
        updatedEvent,
        allOf(
            hasProperty("eventId", equalTo(event.getEventId())),
            hasProperty("eventPlaceEn", equalTo(event.getEventPlaceEn()))));
  }

  @Test
  void createEventTest() {
    // given
    Events events = Events.builder().eventId(MOCK_ID).build();
    EventsDTO eventsDTO = EventsDTO.builder().eventId(MOCK_ID).build();
    when(eventRepository.save(any())).thenReturn(events);

    // when
    EventsDTO createdEvent = eventService.createEvent(eventsDTO);

    // then
    assertThat(createdEvent, hasProperty("eventId", equalTo(events.getEventId())));
  }

  @Test
  void deleteEventTest() {
    // given
    Events events = Events.builder().eventId(MOCK_ID).build();
    when(eventRepository.findById(MOCK_ID)).thenReturn(Optional.of(events));
    doNothing().when(eventRepository).delete(events);

    // when
    eventService.deleteEvent(MOCK_ID);

    // then
    verify(eventRepository, times(1)).delete(events);
  }

  @Test
  void deleteEventWithExceptionTest() {
    doThrow(EventNotFoundException.class).when(eventRepository).findById(MOCK_ID);

    assertThrows(EventNotFoundException.class, () -> eventService.deleteEvent(MOCK_ID));
  }

  @Test
  void calculateEventsNumberTest() {
    // given
    when(eventRepository.calculateEventsNumber()).thenReturn(1);

    // when
    int countEvents = eventService.calculateEventsNumber();

    // then
    assertEquals(1, countEvents);
  }
}
