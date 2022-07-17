package com.epam.spring.web.mvc.conferences.service.impl;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.exception.UserNotFoundException;
import com.epam.spring.web.mvc.conferences.mapper.EventMapper;
import com.epam.spring.web.mvc.conferences.mapper.UserListMapper;
import com.epam.spring.web.mvc.conferences.mapper.UserMapper;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import com.epam.spring.web.mvc.conferences.persistence.dao.UserRepository;
import com.epam.spring.web.mvc.conferences.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDto getUser(String email) {
    log.info("UserService: get user by email {}", email);
    User user = userRepository.findByUserEmail(email).orElseThrow(UserNotFoundException::new);
    return UserMapper.INSTANCE.toDTO(user);
  }

  @Override
  public UserDto createUser(UserDto userDto) {
    User user = UserMapper.INSTANCE.toModel(userDto);
    user = userRepository.save(user);
    log.info("UserService: create user {}", userDto);
    return UserMapper.INSTANCE.toDTO(user);
  }

  @Override
  public UserDto updateUser(String email, UserDto userDto) {
    User user = UserMapper.INSTANCE.toModel(userDto);
    User userFromDB = userRepository.findByUserEmail(email).orElseThrow(UserNotFoundException::new);
    userRepository.delete(userFromDB);
    user = userRepository.save(user);
    log.info("UserService: update user with email {}", email);
    return UserMapper.INSTANCE.toDTO(user);
  }

  @Override
  public void deleteUser(String email) {
    User user = userRepository.findByUserEmail(email).orElseThrow(UserNotFoundException::new);
    userRepository.delete(user);
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
    List<User> userList = userRepository.findAllByRoleId(role_id);
    return UserListMapper.INSTANCE.toDTOList(userList);
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserDto> getUsersByUserAddress(String address, int page, int size) {
    log.info(
        "Got Users by address: {}. Page - {}, number of record per page - {}: ",
        address,
        page,
        size);
    Pageable pageRequest = PageRequest.of(page, size, Sort.by("userName").ascending());
    List<User> userList = userRepository.findAllByUserAddress(address, pageRequest);
    return UserListMapper.INSTANCE.toDTOList(userList);
  }
}
