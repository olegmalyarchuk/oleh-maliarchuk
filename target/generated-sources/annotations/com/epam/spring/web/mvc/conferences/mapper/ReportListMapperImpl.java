package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-03T01:03:03+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class ReportListMapperImpl implements ReportListMapper {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<Reports> toModelList(List<ReportsDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Reports> list = new ArrayList<Reports>( dtos.size() );
        for ( ReportsDTO reportsDTO : dtos ) {
            list.add( reportMapper.toModel( reportsDTO ) );
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
            list.add( reportMapper.toDTO( reports ) );
        }

        return list;
    }
}
