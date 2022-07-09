package com.epam.spring.web.mvc.conferences.dto;

import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import com.epam.spring.web.mvc.conferences.validation.AdvancedInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventsDTO {
    @NotNull
    private int event_id;
    @NotBlank(groups = AdvancedInfo.class)
    private String event_name_ua;
    @NotBlank(groups = AdvancedInfo.class)
    private String event_name_en;
    @NotBlank(groups = AdvancedInfo.class)
    private String event_place_ua;
    @NotBlank(groups = AdvancedInfo.class)
    private String event_place_en;
    @NotBlank(groups = AdvancedInfo.class)
    private String event_description_ua;
    @NotBlank(groups = AdvancedInfo.class)
    private String event_description_en;
    @NotBlank(groups = AdvancedInfo.class)
    private LocalDateTime event_date;
    @NotBlank(groups = AdvancedInfo.class)
    private String event_photo_url;

    private int reportsCount;
    private int registeredCount;
    private int presentCount;
    private List<Reports> reports;
}
