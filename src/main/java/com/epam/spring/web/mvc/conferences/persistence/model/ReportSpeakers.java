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
@Table(name = "reports_speakers")
public class ReportSpeakers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;
    @Column(name = "report_id", nullable = false)
    private int reportId;
    @Column(name = "speaker_id", nullable = false)
    private int speakerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaker_id", updatable = false, insertable = false)
    private User speaker;
}
