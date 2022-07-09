package com.epam.spring.web.mvc.conferences.dto;

import com.epam.spring.web.mvc.conferences.validation.AdvancedInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportsDTO {
    @NotNull
    private int report_id;
    @NotNull
    private int event_id;
    @NotBlank(groups = AdvancedInfo.class)
    private String report_name_ua;
    @NotBlank(groups = AdvancedInfo.class)
    private String report_name_en;
}
