package com.epam.spring.web.mvc.conferences.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report_preposition")
public class ReportPrepositions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "event_id", nullable = false)
    private int eventId;
    @Column(name = "speaker_id", nullable = false)
    private int speakerId;
    @Column(name = "report_name_ua",nullable = false)
    private String reportNameUa;
    @Column(name = "report_name_en", nullable = false)
    private String reportNameEn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaker_id", updatable = false, insertable = false)
    private User speaker;
}
