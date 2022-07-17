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
@Table(name = "event_users")
public class EventUsers {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private int id;

  @Column(name = "user_id", nullable = false)
  private int userId;

  @Column(name = "event_id", nullable = false)
  private int eventId;

  @Column(nullable = false)
  private boolean present;
}
