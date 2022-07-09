package com.epam.spring.web.mvc.conferences.persistence.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "User.findByUser_name", query = "SELECT u FROM User  u WHERE u.userName = ?1")
@NamedNativeQuery(name = "User.findByUser_surname", query = "SELECT * FROM users WHERE user_surname = ?",resultClass = User.class)
public class User {
    @ApiModelProperty(notes = "User ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private int id;
    @ApiModelProperty(notes = "User Role_ID")
    @Column(name = "role_id", nullable = false)
    private int roleId;
    @ApiModelProperty(notes = "User Name")
    @Column(name = "user_name", nullable = false)
    private String userName;
    @ApiModelProperty(notes = "User Surname")
    @Column(name = "user_surname", nullable = false)
    private String userSurname;
    @ApiModelProperty(name = "user_password", notes = "User Password")
    @Column(nullable = false)
    @ToString.Exclude
    private String userPassword;
    @ApiModelProperty(notes = "User Phone")
    @Column(name = "user_phone", nullable = false, unique = true)
    private String userPhone;
    @ApiModelProperty(notes = "User Email")
    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;
    @ApiModelProperty(name = "user_photo_url", notes = "User Photo URL")
    @Column(nullable = false)
    private String userPhotoUrl;
    @ApiModelProperty(notes = "User Address")
    @Column(name = "user_address", nullable = false)
    private String userAddress;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "role_id", insertable = false, updatable = false)
    private UserRoles userRoles;


}
