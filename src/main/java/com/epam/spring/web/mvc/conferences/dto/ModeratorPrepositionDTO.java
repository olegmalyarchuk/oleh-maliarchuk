package com.epam.spring.web.mvc.conferences.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModeratorPrepositionDTO {
  @NotNull private int id;
  @NotNull private int reportId;
  @NotNull private int speakerId;
}
