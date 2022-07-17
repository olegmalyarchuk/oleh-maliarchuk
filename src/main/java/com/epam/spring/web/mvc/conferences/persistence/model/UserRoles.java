package com.epam.spring.web.mvc.conferences.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_roles")
public class UserRoles {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "role_id", nullable = false)
  private int roleId;

  @Column(name = "role_description", nullable = false, unique = true)
  private String roleDescription;

  @JsonIgnore
  @ToString.Exclude
  @OneToMany(mappedBy = "userRoles")
  private List<User> users;
}
