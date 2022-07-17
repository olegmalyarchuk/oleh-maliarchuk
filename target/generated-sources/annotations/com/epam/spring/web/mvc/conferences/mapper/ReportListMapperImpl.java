package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.dto.ReportsDTO.ReportsDTOBuilder;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports.ReportsBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-17T16:15:17+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class ReportListMapperImpl implements ReportListMapper {

    @Override
    public List<Reports> toModelList(List<ReportsDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Reports> list = new ArrayList<Reports>( dtos.size() );
        for ( ReportsDTO reportsDTO : dtos ) {
            list.add( reportsDTOToReports( reportsDTO ) );
        }

        return list;
    }

    @Override
    public List<ReportsDTO> toDTOList(List<Reports> models) {
        if ( models == null ) {
            return null;
        }

        List<ReportsDTO> list = new ArrayList<ReportsDTO>( models.size() );
        for ( Reports reports : models ) {
            list.add( reportsToReportsDTO( reports ) );
        }

        return list;
    }

    protected Reports reportsDTOToReports(ReportsDTO reportsDTO) {
        if ( reportsDTO == null ) {
            return null;
        }

        ReportsBuilder reports = Reports.builder();

        reports.reportId( reportsDTO.getReportId() );
        reports.eventId( reportsDTO.getEventId() );
        reports.reportNameUa( reportsDTO.getReportNameUa() );
        reports.reportNameEn( reportsDTO.getReportNameEn() );

        return reports.build();
    }

    protected ReportsDTO reportsToReportsDTO(Reports reports) {
        if ( reports == null ) {
            return null;
        }

        ReportsDTOBuilder reportsDTO = ReportsDTO.builder();

        reportsDTO.reportId( reports.getReportId() );
        reportsDTO.eventId( reports.getEventId() );
        reportsDTO.reportNameUa( reports.getReportNameUa() );
        reportsDTO.reportNameEn( reports.getReportNameEn() );

        return reportsDTO.build();
    }
}
