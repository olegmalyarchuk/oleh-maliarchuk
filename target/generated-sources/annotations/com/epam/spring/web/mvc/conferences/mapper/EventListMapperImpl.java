package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.dto.EventsDTO.EventsDTOBuilder;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import com.epam.spring.web.mvc.conferences.persistence.model.Events.EventsBuilder;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-17T16:15:17+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class EventListMapperImpl implements EventListMapper {

    @Override
    public List<Events> toModelList(List<EventsDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Events> list = new ArrayList<Events>( dtos.size() );
        for ( EventsDTO eventsDTO : dtos ) {
            list.add( eventsDTOToEvents( eventsDTO ) );
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
            list.add( eventsToEventsDTO( events ) );
        }

        return list;
    }

    protected Events eventsDTOToEvents(EventsDTO eventsDTO) {
        if ( eventsDTO == null ) {
            return null;
        }

        EventsBuilder events = Events.builder();

        events.eventId( eventsDTO.getEventId() );
        events.eventNameUa( eventsDTO.getEventNameUa() );
        events.eventNameEn( eventsDTO.getEventNameEn() );
        events.eventPlaceUa( eventsDTO.getEventPlaceUa() );
        events.eventPlaceEn( eventsDTO.getEventPlaceEn() );
        events.eventDescriptionUa( eventsDTO.getEventDescriptionUa() );
        events.eventDescriptionEn( eventsDTO.getEventDescriptionEn() );
        events.eventDate( eventsDTO.getEventDate() );
        events.eventPhotoUrl( eventsDTO.getEventPhotoUrl() );
        events.reportsCount( eventsDTO.getReportsCount() );
        events.registeredCount( eventsDTO.getRegisteredCount() );
        events.presentCount( eventsDTO.getPresentCount() );
        List<Reports> list = eventsDTO.getReports();
        if ( list != null ) {
            events.reports( new HashSet<Reports>( list ) );
        }

        return events.build();
    }

    protected EventsDTO eventsToEventsDTO(Events events) {
        if ( events == null ) {
            return null;
        }

        EventsDTOBuilder eventsDTO = EventsDTO.builder();

        eventsDTO.eventId( events.getEventId() );
        eventsDTO.eventNameUa( events.getEventNameUa() );
        eventsDTO.eventNameEn( events.getEventNameEn() );
        eventsDTO.eventPlaceUa( events.getEventPlaceUa() );
        eventsDTO.eventPlaceEn( events.getEventPlaceEn() );
        eventsDTO.eventDescriptionUa( events.getEventDescriptionUa() );
        eventsDTO.eventDescriptionEn( events.getEventDescriptionEn() );
        eventsDTO.eventDate( events.getEventDate() );
        eventsDTO.eventPhotoUrl( events.getEventPhotoUrl() );
        eventsDTO.reportsCount( events.getReportsCount() );
        eventsDTO.registeredCount( events.getRegisteredCount() );
        eventsDTO.presentCount( events.getPresentCount() );
        Set<Reports> set = events.getReports();
        if ( set != null ) {
            eventsDTO.reports( new ArrayList<Reports>( set ) );
        }

        return eventsDTO.build();
    }
}
