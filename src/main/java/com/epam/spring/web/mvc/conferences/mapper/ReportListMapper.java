package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportListMapper {
  ReportListMapper INSTANCE = Mappers.getMapper(ReportListMapper.class);

  List<Reports> toModelList(List<ReportsDTO> dtos);

  List<ReportsDTO> toDTOList(List<Reports> models);
}
