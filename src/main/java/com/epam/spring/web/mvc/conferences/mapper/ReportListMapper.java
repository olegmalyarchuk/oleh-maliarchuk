package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.EventsDTO;
import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.persistence.model.Events;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ReportMapper.class)
public interface ReportListMapper {
    List<Reports> toModelList(List<ReportsDTO> dtos);
    List<ReportsDTO> toDTOList(List<Reports> models);
}
