package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.dto.EventsDTO.EventsDTOBuilder;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import com.epam.spring.web.mvc.conferences.persistence.model.Events.EventsBuilder;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-03T01:03:03+0300",
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

        eventsDTO.event_id( model.getEvent_id() );
        eventsDTO.event_name_ua( model.getEvent_name_ua() );
        eventsDTO.event_name_en( model.getEvent_name_en() );
        eventsDTO.event_place_ua( model.getEvent_place_ua() );
        eventsDTO.event_place_en( model.getEvent_place_en() );
        eventsDTO.event_description_ua( model.getEvent_description_ua() );
        eventsDTO.event_description_en( model.getEvent_description_en() );
        eventsDTO.event_date( model.getEvent_date() );
        eventsDTO.event_photo_url( model.getEvent_photo_url() );
        eventsDTO.reportsCount( model.getReportsCount() );
        eventsDTO.registeredCount( model.getRegisteredCount() );
        eventsDTO.presentCount( model.getPresentCount() );
        List<Reports> list = model.getReports();
        if ( list != null ) {
            eventsDTO.reports( new ArrayList<Reports>( list ) );
        }

        return eventsDTO.build();
    }

    @Override
    public Events toModel(EventsDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EventsBuilder events = Events.builder();

        events.event_id( dto.getEvent_id() );
        events.event_name_ua( dto.getEvent_name_ua() );
        events.event_name_en( dto.getEvent_name_en() );
        events.event_place_ua( dto.getEvent_place_ua() );
        events.event_place_en( dto.getEvent_place_en() );
        events.event_description_ua( dto.getEvent_description_ua() );
        events.event_description_en( dto.getEvent_description_en() );
        events.event_date( dto.getEvent_date() );
        events.event_photo_url( dto.getEvent_photo_url() );
        events.reportsCount( dto.getReportsCount() );
        events.registeredCount( dto.getRegisteredCount() );
        events.presentCount( dto.getPresentCount() );
        List<Reports> list = dto.getReports();
        if ( list != null ) {
            events.reports( new ArrayList<Reports>( list ) );
        }

        return events.build();
    }
}
