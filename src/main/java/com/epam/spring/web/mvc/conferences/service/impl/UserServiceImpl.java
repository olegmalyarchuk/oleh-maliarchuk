package com.epam.spring.web.mvc.conferences.service.impl;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.mapper.UserListMapper;
import com.epam.spring.web.mvc.conferences.mapper.UserMapper;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import com.epam.spring.web.mvc.conferences.persistence.dao.UserRepository;
import com.epam.spring.web.mvc.conferences.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    UserMapper mapper;

    @Autowired
    UserListMapper mapperList;

    @Override
    public UserDto getUser(String email) {
        log.info("UserService: get user by email {}", email);
        return  mapper.toDTO(userRepository.getUser(email));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapper.toModel(userDto);
        user = userRepository.createUser(user);
        log.info("UserService: create user {}", userDto);
        return mapper.toDTO(user);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        User user = mapper.toModel(userDto);
        user = userRepository.updateUser(email, user);
        log.info("UserService: update user with email {}", email);
        return mapper.toDTO(user);
    }

    @Override
    public void deleteUser(String email) {
        userRepository.deleteUser(email);
        log.info("UserService: delete user with email {}", email);
    }

    @Override
    public int calculateUsersNumber() {
        log.info("UserService: users count{}", userRepository.calculateUsersNumber());
        return userRepository.calculateUsersNumber();
    }

    @Override
    public List<UserDto> getUsersByRoleId(int role_id) {
        log.info("Got Users by role_id: " + role_id);
        List<User> userList = userRepository.getUsersByRoleId(role_id);
        return mapperList.toDTOList(userList);

    }

}
