package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
  EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

  EventsDTO toDTO(Events model);

  Events toModel(EventsDTO dto);
}
