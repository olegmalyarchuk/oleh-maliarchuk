package com.epam.spring.web.mvc.conferences.persistence.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Report_speakers {
    private int id;
    private int report_id;
    private int speaker_id;
    private User speaker;
}
