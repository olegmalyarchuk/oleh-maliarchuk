package com.epam.spring.web.mvc.conferences.persistence.dao;

import com.epam.spring.web.mvc.conferences.persistence.model.Reports;

import java.util.List;

public interface ReportRepository {
    Reports getReport(int id);

    Reports createReport(Reports reports);

    Reports updateReport(int id, Reports reports);

    void deleteReport(int id);

    int calculateReportsNumber();

    List<Reports> getReportByEventId(int event_id);
}
