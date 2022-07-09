package com.epam.spring.web.mvc.conferences.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User_rolesDTO {
    @NotNull
    private int role_id;
    @NotNull
    @Size(max = 24)
    private String role_description;
}
