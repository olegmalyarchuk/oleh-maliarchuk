package com.epam.spring.web.mvc.conferences.persistence.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Report_prepositions {
    private int id;
    private int event_id;
    private int speaker_id;
    private String report_name_ua;
    private String report_name_en;
}
