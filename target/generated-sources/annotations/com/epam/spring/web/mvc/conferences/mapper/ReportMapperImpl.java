package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.dto.ReportsDTO.ReportsDTOBuilder;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports.ReportsBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-03T01:03:03+0300",
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

        reportsDTO.report_id( model.getReport_id() );
        reportsDTO.event_id( model.getEvent_id() );
        reportsDTO.report_name_ua( model.getReport_name_ua() );
        reportsDTO.report_name_en( model.getReport_name_en() );

        return reportsDTO.build();
    }

    @Override
    public Reports toModel(ReportsDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ReportsBuilder reports = Reports.builder();

        reports.report_id( dto.getReport_id() );
        reports.event_id( dto.getEvent_id() );
        reports.report_name_ua( dto.getReport_name_ua() );
        reports.report_name_en( dto.getReport_name_en() );

        return reports.build();
    }
}
