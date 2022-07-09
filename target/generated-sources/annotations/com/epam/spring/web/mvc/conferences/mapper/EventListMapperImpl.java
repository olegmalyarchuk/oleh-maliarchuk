package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-03T01:03:03+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class EventListMapperImpl implements EventListMapper {

    @Autowired
    private EventMapper eventMapper;

    @Override
    public List<Events> toModelList(List<EventsDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Events> list = new ArrayList<Events>( dtos.size() );
        for ( EventsDTO eventsDTO : dtos ) {
            list.add( eventMapper.toModel( eventsDTO ) );
        }

        return list;
    }

    @Override
    public List<EventsDTO> toDTOList(List<Events> models) {
        if ( models == null ) {
            return null;
        }

        List<EventsDTO> list = new ArrayList<EventsDTO>( models.size() );
        for ( Events events : models ) {
            list.add( eventMapper.toDTO( events ) );
        }

        return list;
    }
}
