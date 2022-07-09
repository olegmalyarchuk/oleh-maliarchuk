package com.epam.spring.web.mvc.conferences.service.impl;

import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.mapper.ReportListMapper;
import com.epam.spring.web.mvc.conferences.mapper.ReportMapper;
import com.epam.spring.web.mvc.conferences.mapper.UserListMapper;
import com.epam.spring.web.mvc.conferences.mapper.UserMapper;
import com.epam.spring.web.mvc.conferences.persistence.dao.ReportRepository;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import com.epam.spring.web.mvc.conferences.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    ReportMapper mapper;

    @Autowired
    ReportListMapper mapperList;


    @Override
    public ReportsDTO getReport(int id) {
        log.info("ReportService: get report by id {}", id);
        return mapper.toDTO(reportRepository.getReport(id));
    }

    @Override
    public ReportsDTO createReport(ReportsDTO reportsDTO) {
        Reports reports = mapper.toModel(reportsDTO);
        reports = reportRepository.createReport(reports);
        log.info("ReportService: create report {}", reportsDTO);
        return mapper.toDTO(reports);
    }

    @Override
    public ReportsDTO updateReport(int id, ReportsDTO reportsDTO) {
        Reports reports = mapper.toModel(reportsDTO);
        reports = reportRepository.updateReport(id, reports);
        log.info("ReportService: update report with id {}", id);
        return mapper.toDTO(reports);
    }

    @Override
    public void deleteReport(int id) {
        reportRepository.deleteReport(id);
        log.info("ReportService: delete report with id {}", id);
    }

    @Override
    public int calculateReportsNumber() {
        log.info("ReportService: reports count{}", reportRepository.calculateReportsNumber());
        return reportRepository.calculateReportsNumber();
    }

    @Override
    public List<ReportsDTO> getReportByEventId(int event_id) {
        log.info("Got Reports by event_id: " + event_id);
        List<Reports> reportsList = reportRepository.getReportByEventId(event_id);
        return mapperList.toDTOList(reportsList);
    }

}
