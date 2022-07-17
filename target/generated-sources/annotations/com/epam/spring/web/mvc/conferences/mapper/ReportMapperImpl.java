package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.dto.ReportsDTO.ReportsDTOBuilder;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports.ReportsBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-17T16:15:17+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class ReportMapperImpl implements ReportMapper {

    @Override
    public ReportsDTO toDTO(Reports model) {
        if ( model == null ) {
            return null;
        }

        ReportsDTOBuilder reportsDTO = ReportsDTO.builder();

        reportsDTO.reportId( model.getReportId() );
        reportsDTO.eventId( model.getEventId() );
        reportsDTO.reportNameUa( model.getReportNameUa() );
        reportsDTO.reportNameEn( model.getReportNameEn() );

        return reportsDTO.build();
    }

    @Override
    public Reports toModel(ReportsDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ReportsBuilder reports = Reports.builder();

        reports.reportId( dto.getReportId() );
        reports.eventId( dto.getEventId() );
        reports.reportNameUa( dto.getReportNameUa() );
        reports.reportNameEn( dto.getReportNameEn() );

        return reports.build();
    }
}
