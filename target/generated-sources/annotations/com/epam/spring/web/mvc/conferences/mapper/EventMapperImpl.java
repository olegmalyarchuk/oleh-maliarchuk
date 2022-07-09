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
    date = "2022-07-09T22:36:47+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventsDTO toDTO(Events model) {
        if ( model == null ) {
            return null;
        }

        EventsDTOBuilder eventsDTO = EventsDTO.builder();

        eventsDTO.eventId( model.getEventId() );
        eventsDTO.eventNameUa( model.getEventNameUa() );
        eventsDTO.eventNameEn( model.getEventNameEn() );
        eventsDTO.eventPlaceUa( model.getEventPlaceUa() );
        eventsDTO.eventPlaceEn( model.getEventPlaceEn() );
        eventsDTO.eventDescriptionUa( model.getEventDescriptionUa() );
        eventsDTO.eventDescriptionEn( model.getEventDescriptionEn() );
        eventsDTO.eventDate( model.getEventDate() );
        eventsDTO.eventPhotoUrl( model.getEventPhotoUrl() );
        eventsDTO.reportsCount( model.getReportsCount() );
        eventsDTO.registeredCount( model.getRegisteredCount() );
        eventsDTO.presentCount( model.getPresentCount() );
        Set<Reports> set = model.getReports();
        if ( set != null ) {
            eventsDTO.reports( new ArrayList<Reports>( set ) );
        }

        return eventsDTO.build();
    }

    @Override
    public Events toModel(EventsDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EventsBuilder events = Events.builder();

        events.eventId( dto.getEventId() );
        events.eventNameUa( dto.getEventNameUa() );
        events.eventNameEn( dto.getEventNameEn() );
        events.eventPlaceUa( dto.getEventPlaceUa() );
        events.eventPlaceEn( dto.getEventPlaceEn() );
        events.eventDescriptionUa( dto.getEventDescriptionUa() );
        events.eventDescriptionEn( dto.getEventDescriptionEn() );
        events.eventDate( dto.getEventDate() );
        events.eventPhotoUrl( dto.getEventPhotoUrl() );
        events.reportsCount( dto.getReportsCount() );
        events.registeredCount( dto.getRegisteredCount() );
        events.presentCount( dto.getPresentCount() );
        List<Reports> list = dto.getReports();
        if ( list != null ) {
            events.reports( new HashSet<Reports>( list ) );
        }

        return events.build();
    }
}
