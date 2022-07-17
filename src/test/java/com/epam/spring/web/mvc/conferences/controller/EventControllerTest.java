package com.epam.spring.web.mvc.conferences.controller;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.service.EventService;
import com.epam.spring.web.mvc.conferences.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(value = EventController.class)
@AutoConfigureMockMvc
public class EventControllerTest {

  private static final int MOCK_ID = 1;
  private static final int MOCK_UPDATED_ID = 2;
  private static final String MOCK_LOCATION_EN = "Lviv";
  private static final String MOCK_UPDATED_LOCATION_EN = "Kyib";
  private static final String MOCK_LOCATION_UA = "Львів";

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockBean private EventService eventService;

  @Test
  void getEventTest() throws Exception {
    EventsDTO eventsDTO = EventsDTO.builder().eventId(MOCK_ID).build();

    when(eventService.getEvent(MOCK_ID)).thenReturn(eventsDTO);

    mockMvc
        .perform(get("/events/" + MOCK_ID))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.eventId").value(eventsDTO.getEventId()));
  }

  @Test
  void createEventTest() throws Exception {
    EventsDTO eventsDTO = EventsDTO.builder().eventId(MOCK_ID).build();

    when(eventService.createEvent(eventsDTO)).thenReturn(eventsDTO);

    mockMvc
        .perform(
            post("/events")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(eventsDTO)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.eventId").value(eventsDTO.getEventId()));

    verify(eventService, times(1)).createEvent(eventsDTO);
  }

  @Test
  void updateEventTest() throws Exception {
    EventsDTO eventsDTO =
        EventsDTO.builder().eventId(MOCK_ID).eventPlaceEn(MOCK_LOCATION_EN).build();

    when(eventService.updateEvent(MOCK_ID, eventsDTO)).thenReturn(eventsDTO);

    mockMvc
        .perform(
            put("/events/" + MOCK_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(eventsDTO)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.eventId").value(eventsDTO.getEventId()));

    verify(eventService, times(1)).updateEvent(MOCK_ID, eventsDTO);
  }

  @Test
  void deleteEventTest() throws Exception {
    doNothing().when(eventService).deleteEvent(MOCK_ID);

    mockMvc.perform(delete("/events/" + MOCK_ID)).andDo(print()).andExpect(status().isNoContent());

    verify(eventService, times(1)).deleteEvent(MOCK_ID);
  }

  @Test
  void calculateEventsNumberTest() throws Exception {
    when(eventService.calculateEventsNumber()).thenReturn(0);

    mockMvc
        .perform(get("/events/count"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
  }

  @Test
  void getEventByPlaceUATest() throws Exception {
    EventsDTO eventsDTO =
        EventsDTO.builder().eventId(MOCK_ID).eventPlaceUa(MOCK_LOCATION_UA).build();
    List<EventsDTO> eventsDTOList = new ArrayList<>();
    eventsDTOList.add(eventsDTO);

    when(eventService.getEventByPlaceUA(MOCK_LOCATION_UA)).thenReturn(eventsDTOList);

    mockMvc
        .perform(get("/events/placeUA/" + MOCK_LOCATION_UA))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].eventId").value(eventsDTO.getEventId()));
  }

  @Test
  void getEventByPlaceENTest() throws Exception {
    EventsDTO eventsDTO =
        EventsDTO.builder().eventId(MOCK_ID).eventPlaceUa(MOCK_LOCATION_EN).build();
    List<EventsDTO> eventsDTOList = new ArrayList<>();
    eventsDTOList.add(eventsDTO);

    when(eventService.getEventByPlaceEN(MOCK_LOCATION_EN)).thenReturn(eventsDTOList);

    mockMvc
        .perform(get("/events/placeEN/" + MOCK_LOCATION_EN))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].eventId").value(eventsDTO.getEventId()));
  }
}
