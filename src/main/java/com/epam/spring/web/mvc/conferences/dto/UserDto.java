package com.epam.spring.web.mvc.conferences.dto;

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
  private int roleId;

  @Pattern(regexp = "^[A-ZА-Я][\\p{Alpha}А-Яа-я\\s-]{1,25}", groups = BasicInfo.class)
  @NotBlank(message = "{validation.username.not_null", groups = AdvancedInfo.class)
  private String userName;

  @Pattern(regexp = "^[A-ZА-Я][\\p{Alpha}А-Яа-я\\s-]{1,25}", groups = BasicInfo.class)
  @NotBlank(message = "{validation.usersurname.not_null}", groups = AdvancedInfo.class)
  private String userSurname;

  @Pattern(regexp = "^([a-zA-Z0-9@*#]{4,16})$", groups = BasicInfo.class)
  @NotBlank(message = "{validation.userpassword.not_null}", groups = AdvancedInfo.class)
  private String userPassword;

  @Phone(groups = BasicInfo.class)
  @NotBlank(message = "{validation.userphone.not_null}", groups = AdvancedInfo.class)
  private String userPhone;

  @Email
  @NotBlank(message = "{validation.useremail.not_null}", groups = AdvancedInfo.class)
  private String userEmail;

  @NotBlank(message = "{validation.userphotourl.not_null}", groups = AdvancedInfo.class)
  private String userPhotoUrl;

  @NotBlank(message = "{validation.useraddress.not_null}", groups = AdvancedInfo.class)
  private String userAddress;
}
