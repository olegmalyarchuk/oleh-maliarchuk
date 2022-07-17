package com.epam.spring.web.mvc.conferences.service.impl;

import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.exception.ReportNotFoundException;
import com.epam.spring.web.mvc.conferences.persistence.dao.ReportRepository;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {

  private static final int MOCK_ID = 1;
  private static final int MOCK_UPDATED_ID = 2;

  @InjectMocks private ReportServiceImpl reportService;
  @Mock private ReportRepository reportRepository;

  @Test
  void getReportByIdTest() {
    // given
    Reports expectedReport = Reports.builder().reportId(MOCK_ID).build();
    when(reportRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedReport));

    // when
    ReportsDTO actualReport = reportService.getReport(MOCK_ID);

    // then
    assertEquals(expectedReport.getReportId(), actualReport.getReportId());
  }

  @Test
  void getReportsByEventIdTest() {
    // given
    Reports report = Reports.builder().reportId(MOCK_ID).eventId(MOCK_ID).build();
    List<Reports> reportsList = new ArrayList<>();
    reportsList.add(report);
    when(reportRepository.findAllByEventId(MOCK_ID)).thenReturn(reportsList);

    // when
    List<ReportsDTO> actualReports = reportService.getReportByEventId(MOCK_ID);

    // then
    assertEquals(actualReports.size(), reportsList.size());
  }

  @Test
  void updateReportTest() {
    // given
    Reports report = Reports.builder().reportId(1).eventId(MOCK_ID).build();
    ReportsDTO updatedReport =
        ReportsDTO.builder().reportId(MOCK_ID).eventId(MOCK_UPDATED_ID).build();
    when(reportRepository.findById(MOCK_ID)).thenReturn(Optional.of(report));
    when(reportRepository.save(any())).thenReturn(report);

    // when
    updatedReport = reportService.updateReport(MOCK_ID, updatedReport);

    // then
    assertThat(
        updatedReport,
        allOf(
            hasProperty("reportId", equalTo(report.getReportId())),
            hasProperty("eventId", equalTo(report.getEventId()))));
  }

  @Test
  void createReportTest() {
    // given
    Reports report = Reports.builder().reportId(MOCK_ID).build();
    ReportsDTO reportsDTO = ReportsDTO.builder().reportId(MOCK_ID).build();
    when(reportRepository.save(any())).thenReturn(report);

    // when
    ReportsDTO createdReport = reportService.createReport(reportsDTO);

    // then
    assertThat(createdReport, hasProperty("reportId", equalTo(report.getReportId())));
  }

  @Test
  void deleteReportTest() {
    // given
    Reports report = Reports.builder().reportId(MOCK_ID).build();
    when(reportRepository.findById(MOCK_ID)).thenReturn(Optional.of(report));
    doNothing().when(reportRepository).delete(report);

    // when
    reportService.deleteReport(MOCK_ID);

    // then
    verify(reportRepository, times(1)).delete(report);
  }

  @Test
  void deleteReportWithExceptionTest() {
    doThrow(ReportNotFoundException.class).when(reportRepository).findById(MOCK_ID);

    assertThrows(ReportNotFoundException.class, () -> reportService.deleteReport(MOCK_ID));
  }

  @Test
  void calculateReportsNumberTest() {
    // given
    when(reportRepository.calculateReportsNumber()).thenReturn(1);

    // when
    int countReports = reportService.calculateReportsNumber();

    // then
    assertEquals(1, countReports);
  }
}
