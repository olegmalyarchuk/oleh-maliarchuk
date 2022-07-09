package com.epam.spring.web.mvc.conferences.service;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto getUser(String email);

    @Transactional
    UserDto createUser(UserDto userDto);

    UserDto updateUser(String email, UserDto userDto);

    void deleteUser(String email);

    int calculateUsersNumber();

    List<UserDto> getUsersByRoleId(int role_id);

    List<UserDto> getUsersByUserAddress(String address, int page, int size);

}
