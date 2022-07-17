package com.epam.spring.web.mvc.conferences.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reports")
public class Reports {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "report_id", nullable = false)
  private int reportId;

  @Column(name = "event_id", nullable = false)
  private int eventId;

  @Column(name = "report_name_ua", nullable = false)
  private String reportNameUa;

  @Column(name = "report_name_en", nullable = false)
  private String reportNameEn;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private ReportSpeakers report_speakers;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "report_id")
  private Set<SpeakerPreposition> speaker_prepositions = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "report_id", updatable = false, insertable = false)
  private Set<ModeratorPreposition> moderatorPrepositions = new HashSet<>();
}
