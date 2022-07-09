package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventsDTO toDTO(Events model);
    Events toModel(EventsDTO dto);
}
