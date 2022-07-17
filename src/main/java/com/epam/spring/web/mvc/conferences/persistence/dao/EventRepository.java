package com.epam.spring.web.mvc.conferences.persistence.dao;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Events, Integer> {
  @Query(
      nativeQuery = true,
      value = "SELECT event_id as ROWCOUNT FROM events order by event_id desc limit 1")
  int calculateEventsNumber();

  List<Events> findAllByEventPlaceUa(String event_place_ua);

  List<Events> findAllByEventPlaceEn(String event_place_en);
}
