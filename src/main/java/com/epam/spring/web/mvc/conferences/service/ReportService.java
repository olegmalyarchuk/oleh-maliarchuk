package com.epam.spring.web.mvc.conferences.service;

import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.persistence.model.User;

import java.util.List;

public interface ReportService {
  ReportsDTO getReport(int id);

  ReportsDTO createReport(ReportsDTO reportsDTO);

  ReportsDTO updateReport(int id, ReportsDTO reportsDTO);

  void deleteReport(int id);

  int calculateReportsNumber();

  List<ReportsDTO> getReportByEventId(int event_id);
}
