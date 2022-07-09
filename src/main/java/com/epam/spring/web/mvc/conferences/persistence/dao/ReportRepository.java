package com.epam.spring.web.mvc.conferences.persistence.dao;

import com.epam.spring.web.mvc.conferences.persistence.model.Reports;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Reports, Integer> {
    @Query(nativeQuery = true, value = "SELECT report_id as ROWCOUNT FROM reports order by report_id desc limit 1")
    int calculateReportsNumber();

    List<Reports> findAllByEventId(int event_id);

}
