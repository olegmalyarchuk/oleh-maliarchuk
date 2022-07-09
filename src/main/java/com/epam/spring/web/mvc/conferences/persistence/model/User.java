package com.epam.spring.web.mvc.conferences.persistence.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    @ApiModelProperty(notes = "User ID")
    private int id;
    @ApiModelProperty(notes = "User Role_ID")
    private int role_id;
    @ApiModelProperty(notes = "User Name")
    private String user_name;
    @ApiModelProperty(notes = "User Surname")
    private String user_surname;
    @ApiModelProperty(notes = "User Password")
    private String user_password;
    @ApiModelProperty(notes = "User Phone")
    private String user_phone;
    @ApiModelProperty(notes = "User Email")
    private String user_email;
    @ApiModelProperty(notes = "User Photo URL")
    private String user_photo_url;
    @ApiModelProperty(notes = "User Address")
    private String user_address;
    @ApiModelProperty(notes = "User role")
    private User_roles user_roles;
}
