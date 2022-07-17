package com.epam.spring.web.mvc.conferences.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Events {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "event_id", nullable = false)
  private int eventId;

  @Column(name = "event_name_ua", nullable = false)
  private String eventNameUa;

  @Column(name = "event_name_en", nullable = false)
  private String eventNameEn;

  @Column(name = "event_place_ua", nullable = false)
  private String eventPlaceUa;

  @Column(name = "event_place_en", nullable = false)
  private String eventPlaceEn;

  @Column(name = "event_description_ua", nullable = false)
  private String eventDescriptionUa;

  @Column(name = "event_description_en", nullable = false)
  private String eventDescriptionEn;

  @Column(name = "event_date", nullable = false)
  private LocalDateTime eventDate;

  @Column(name = "event_photo_url", nullable = false)
  private String eventPhotoUrl;

  @Transient private int reportsCount;
  @Transient private int registeredCount;
  @Transient private int presentCount;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "event_id")
  private Set<Reports> reports = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "event_id")
  private Set<ModeratorPreposition> moderatorPrepositions = new HashSet<>();
}
