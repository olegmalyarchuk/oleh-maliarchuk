package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportsDTO toDTO(Reports model);
    Reports toModel(ReportsDTO dto);
}
