package com.epam.spring.web.mvc.conferences.controller;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.service.EventService;
import com.epam.spring.web.mvc.conferences.validation.BasicInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Api(value = "conferences", description = "Operations related to events in Conference system")
public class EventController {
  private final EventService eventService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Get event by id")
  public EventsDTO getEvent(@PathVariable int id) {
    log.info("Getting Event with id{}", id);
    return eventService.getEvent(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  @ApiOperation(value = "Create new event")
  public EventsDTO createEvent(@Validated(BasicInfo.class) @RequestBody EventsDTO eventsDTO) {
    log.info("creating event{}", eventsDTO);
    return eventService.createEvent(eventsDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/{id}")
  @ApiOperation(value = "Update event by id")
  public EventsDTO updateEvent(@PathVariable int id, @RequestBody EventsDTO eventsDTO) {
    log.info("Updating event with id{}", id);
    return eventService.updateEvent(id, eventsDTO);
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Delete event by id")
  public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
    log.info("Deleting event with id{}", id);
    eventService.deleteEvent(id);
    return ResponseEntity.noContent().build();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/count")
  @ApiOperation(value = "Show count of events")
  public int calculateEventsNumber() {
    log.info("Calculating events number{}", eventService.calculateEventsNumber());
    return eventService.calculateEventsNumber();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/placeUA/{event_place_ua}")
  @ApiOperation(value = "Get events by place_ua")
  public List<EventsDTO> getEventByPlaceUA(@PathVariable String event_place_ua) {
    log.info("Getting Events by event_place_ua {}", event_place_ua);
    return eventService.getEventByPlaceUA(event_place_ua);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/placeEN/{event_place_en}")
  @ApiOperation(value = "Get events by place_en")
  public List<EventsDTO> getEventByPlaceEN(@PathVariable String event_place_en) {
    log.info("Getting Events by event_place_en {}", event_place_en);
    return eventService.getEventByPlaceEN(event_place_en);
  }
}
