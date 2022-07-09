package com.epam.spring.web.mvc.conferences.dto;

import com.epam.spring.web.mvc.conferences.persistence.model.User_roles;
import com.epam.spring.web.mvc.conferences.validation.AdvancedInfo;
import com.epam.spring.web.mvc.conferences.validation.BasicInfo;
import com.epam.spring.web.mvc.conferences.validation.Phone;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @NotNull(message = "{validation.id.not_null")
    private int id;
    @NotNull(message = "{validation.role_id.not_null")
    private int role_id;
    @Pattern(regexp = "^[A-ZА-Я][\\p{Alpha}А-Яа-я\\s-]{1,25}", groups = BasicInfo.class)
    @NotBlank(message = "{validation.username.not_null", groups = AdvancedInfo.class)
    private String user_name;
    @Pattern(regexp = "^[A-ZА-Я][\\p{Alpha}А-Яа-я\\s-]{1,25}", groups = BasicInfo.class)
    @NotBlank(message = "{validation.usersurname.not_null}", groups = AdvancedInfo.class)
    private String user_surname;
    @Pattern(regexp = "^([a-zA-Z0-9@*#]{4,16})$", groups = BasicInfo.class)
    @NotBlank(message = "{validation.userpassword.not_null}", groups = AdvancedInfo.class)
    private String user_password;
    @Phone(groups = BasicInfo.class)
    @NotBlank(message = "{validation.userphone.not_null}", groups = AdvancedInfo.class)
    private String user_phone;
    @Email
    @NotBlank(message = "{validation.useremail.not_null}", groups = AdvancedInfo.class)
    private String user_email;
    @NotBlank(message = "{validation.userphotourl.not_null}", groups = AdvancedInfo.class)
    private String user_photo_url;
    @NotBlank(message = "{validation.useraddress.not_null}", groups = AdvancedInfo.class)
    private String user_address;
}
