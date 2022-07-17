package com.epam.spring.web.mvc.conferences.controller;

import com.epam.spring.web.mvc.conferences.dto.ReportsDTO;
import com.epam.spring.web.mvc.conferences.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(value = ReportController.class)
@AutoConfigureMockMvc
public class ReportControllerTest {

  private static final int MOCK_ID = 1;
  private static final int MOCK_UPDATED_ID = 2;
  private static final String MOCK_REPORT_NAME = "Report";

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockBean private ReportService reportService;

  @Test
  void getReportTest() throws Exception {
    ReportsDTO reportsDTO = ReportsDTO.builder().reportId(MOCK_ID).build();

    when(reportService.getReport(MOCK_ID)).thenReturn(reportsDTO);

    mockMvc
        .perform(get("/reports/" + MOCK_ID))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.reportId").value(reportsDTO.getReportId()));
  }

  @Test
  void createReportTest() throws Exception {
    ReportsDTO reportsDTO =
        ReportsDTO.builder()
            .reportId(MOCK_ID)
            .eventId(MOCK_ID)
            .reportNameEn(MOCK_REPORT_NAME)
            .reportNameUa(MOCK_REPORT_NAME)
            .build();

    when(reportService.createReport(reportsDTO)).thenReturn(reportsDTO);

    mockMvc
        .perform(
            post("/reports")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reportsDTO)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.reportId").value(reportsDTO.getReportId()));

    verify(reportService, times(1)).createReport(reportsDTO);
  }

  @Test
  void updateReportTest() throws Exception {
    ReportsDTO reportsDTO =
        ReportsDTO.builder()
            .reportId(MOCK_ID)
            .eventId(MOCK_ID)
            .reportNameEn(MOCK_REPORT_NAME)
            .reportNameUa(MOCK_REPORT_NAME)
            .build();

    when(reportService.updateReport(MOCK_ID, reportsDTO)).thenReturn(reportsDTO);

    mockMvc
        .perform(
            put("/reports/" + MOCK_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reportsDTO)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.reportId").value(reportsDTO.getReportId()));

    verify(reportService, times(1)).updateReport(MOCK_ID, reportsDTO);
  }

  @Test
  void deleteReportTest() throws Exception {
    doNothing().when(reportService).deleteReport(MOCK_ID);

    mockMvc.perform(delete("/reports/" + MOCK_ID)).andDo(print()).andExpect(status().isNoContent());

    verify(reportService, times(1)).deleteReport(MOCK_ID);
  }

  @Test
  void calculateReportsNumberTest() throws Exception {
    when(reportService.calculateReportsNumber()).thenReturn(0);

    mockMvc
        .perform(get("/reports/count"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
  }

  @Test
  void getReportByEventIdTest() throws Exception {
    ReportsDTO reportsDTO = ReportsDTO.builder().reportId(MOCK_ID).eventId(MOCK_ID).build();
    List<ReportsDTO> reportsDTOList = new ArrayList<>();
    reportsDTOList.add(reportsDTO);

    when(reportService.getReportByEventId(MOCK_ID)).thenReturn(reportsDTOList);

    mockMvc
        .perform(get("/reports/getByEventId/" + MOCK_ID))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].reportId").value(reportsDTO.getReportId()));
  }
}
