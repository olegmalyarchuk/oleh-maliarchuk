package com.epam.spring.web.mvc.conferences.service;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.persistence.model.User;

import java.util.List;

public interface UserService {
    UserDto getUser(String email);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String email, UserDto userDto);

    void deleteUser(String email);

    int calculateUsersNumber();

    List<UserDto> getUsersByRoleId(int role_id);

}
