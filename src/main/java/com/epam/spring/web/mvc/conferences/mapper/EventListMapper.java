package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventListMapper {
  EventListMapper INSTANCE = Mappers.getMapper(EventListMapper.class);

  List<Events> toModelList(List<EventsDTO> dtos);

  List<EventsDTO> toDTOList(List<Events> models);
}
