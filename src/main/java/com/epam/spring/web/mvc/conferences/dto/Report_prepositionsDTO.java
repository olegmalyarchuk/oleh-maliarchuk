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
public class Report_prepositionsDTO {
  @NotNull private int id;
  @NotNull private int eventId;
  @NotNull private int speakerId;

  @NotBlank(groups = AdvancedInfo.class)
  private String reportNameUa;

  @NotBlank(groups = AdvancedInfo.class)
  private String reportNameEn;
}
