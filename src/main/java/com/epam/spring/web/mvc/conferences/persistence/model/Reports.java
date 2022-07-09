package com.epam.spring.web.mvc.conferences.persistence.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reports {
    private int report_id;
    private int event_id;
    private String report_name_ua;
    private String report_name_en;

    private Report_speakers report_speaker;
}
