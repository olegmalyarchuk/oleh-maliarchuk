package com.epam.spring.web.mvc.conferences.persistence.dao.impl;

import com.epam.spring.web.mvc.conferences.exception.ReportNotFoundException;
import com.epam.spring.web.mvc.conferences.persistence.dao.ReportRepository;
import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ReportRepositoryImpl implements ReportRepository {
    private final List<Reports> list = new ArrayList<>();

    @Override
    public Reports getReport(int id) {
        log.info("Got Report by id: " + id);
        return list.stream().filter(reports -> reports.getReport_id()==id)
                .findFirst().orElseThrow(ReportNotFoundException::new);
    }

    @Override
    public Reports createReport(Reports reports) {
        list.add(reports);
        log.info("Created Report " + reports);
        return reports;
    }

    @Override
    public Reports updateReport(int id, Reports reports) {
        boolean isUpdated = list.removeIf(r -> r.getReport_id()==id);
        if (isUpdated) {
            list.add(reports);
        } else {
            throw new ReportNotFoundException();
        }
        log.info("Report with id " + id + " was updated");
        return reports;
    }

    @Override
    public void deleteReport(int id) {
        list.removeIf(reports -> reports.getEvent_id()==id);
        log.info("Report with id {} was deleted", id);
    }

    @Override
    public int calculateReportsNumber() {
        log.info("Reports number{}", list.size());
        return list.size();
    }

    @Override
    public List<Reports> getReportByEventId(int event_id) {
        log.info("Got Reports by event_id: " + event_id);
        return list.stream().filter(reports -> reports.getEvent_id()==event_id).collect(Collectors.toList());
    }

}
