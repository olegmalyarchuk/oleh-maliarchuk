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
  @NotNull private int eventId;

  @NotBlank(groups = AdvancedInfo.class)
  private String eventNameUa;

  @NotBlank(groups = AdvancedInfo.class)
  private String eventNameEn;

  @NotBlank(groups = AdvancedInfo.class)
  private String eventPlaceUa;

  @NotBlank(groups = AdvancedInfo.class)
  private String eventPlaceEn;

  @NotBlank(groups = AdvancedInfo.class)
  private String eventDescriptionUa;

  @NotBlank(groups = AdvancedInfo.class)
  private String eventDescriptionEn;

  @NotBlank(groups = AdvancedInfo.class)
  private LocalDateTime eventDate;

  @NotBlank(groups = AdvancedInfo.class)
  private String eventPhotoUrl;

  private int reportsCount;
  private int registeredCount;
  private int presentCount;
  private List<Reports> reports;
}
