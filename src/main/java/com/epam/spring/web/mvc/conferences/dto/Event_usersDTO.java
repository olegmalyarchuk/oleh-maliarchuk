package com.epam.spring.web.mvc.conferences.dto;

import com.epam.spring.web.mvc.conferences.persistence.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event_usersDTO {
    @NotNull
    private int id;
    @NotNull
    private int user_id;
    @NotNull
    private int event_id;
    @NotNull
    private boolean present;
    private User user;
}
