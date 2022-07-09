package com.epam.spring.web.mvc.conferences.persistence.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Events {
    private int event_id;
    private String event_name_ua;
    private String event_name_en;
    private String event_place_ua;
    private String event_place_en;
    private String event_description_ua;
    private String event_description_en;
    private LocalDateTime event_date;
    private String event_photo_url;

    private int reportsCount;
    private int registeredCount;
    private int presentCount;
    private List<Reports> reports;
}
