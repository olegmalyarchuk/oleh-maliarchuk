package com.epam.spring.web.mvc.conferences.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.exception.UserNotFoundException;
import com.epam.spring.web.mvc.conferences.mapper.UserMapper;
import com.epam.spring.web.mvc.conferences.persistence.dao.UserRepository;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

  private static final String MOCK_EMAIL = "speaker@gmail.com";
  private static final String MOCK_PHONE = "+123456789";
  private static final String MOCK_ADDRESS = "Lviv";
  private static final String MOCK_UPDATED_EMAIL = "moderator@gmail.com";
  private static final String MOCK_UPDATED_PHONE = "+987654321";
  @InjectMocks private UserServiceImpl userService;
  @Mock private UserRepository userRepository;

  @Test
  void getUserByEmailTest() {
    // given
    User expectedUser = User.builder().userEmail(MOCK_EMAIL).build();
    when(userRepository.findByUserEmail(MOCK_EMAIL)).thenReturn(Optional.of(expectedUser));

    // when
    UserDto actualUser = userService.getUser(MOCK_EMAIL);

    // then
    assertEquals(expectedUser.getUserEmail(), actualUser.getUserEmail());
  }

  @Test
  void getUsersByRoleIdTest() {
    // given
    User user = User.builder().userEmail(MOCK_EMAIL).roleId(3).build();
    List<User> userList = new ArrayList<>();
    userList.add(user);
    when(userRepository.findAllByRoleId(3)).thenReturn(userList);

    // when
    List<UserDto> actualUsers = userService.getUsersByRoleId(3);

    // then
    assertEquals(actualUsers.size(), userList.size());
  }

  @Test
  void getUsersByUserAddressTest() {
    // given
    UserDto userDto1 = UserDto.builder().userAddress(MOCK_ADDRESS).build();
    UserDto userDto2 = UserDto.builder().userAddress(MOCK_ADDRESS).build();
    UserDto userDto3 = UserDto.builder().userAddress(MOCK_ADDRESS).build();
    List<User> users =
        Arrays.asList(
            UserMapper.INSTANCE.toModel(userDto1),
            UserMapper.INSTANCE.toModel(userDto2),
            UserMapper.INSTANCE.toModel(userDto3));

    Pageable pageable = PageRequest.of(0, 2, Sort.by("userName").ascending());
    when(userRepository.findAllByUserAddress(MOCK_ADDRESS, pageable))
        .thenReturn(users.subList(0, 2));

    // when
    List<UserDto> actualUsers = userService.getUsersByUserAddress(MOCK_ADDRESS, 0, 2);

    // then
    assertThat(actualUsers.size(), is(2));
  }

  @Test
  void updateUserTest() {
    // given
    User user = User.builder().userEmail(MOCK_EMAIL).userPhone(MOCK_PHONE).build();
    UserDto updatedUser =
        UserDto.builder().userEmail(MOCK_UPDATED_EMAIL).userPhone(MOCK_UPDATED_PHONE).build();
    when(userRepository.findByUserEmail(MOCK_EMAIL)).thenReturn(Optional.of(user));
    when(userRepository.save(any())).thenReturn(user);

    // when
    updatedUser = userService.updateUser(MOCK_EMAIL, updatedUser);

    // then
    assertThat(
        updatedUser,
        allOf(
            hasProperty("userEmail", equalTo(user.getUserEmail())),
            hasProperty("userPhone", equalTo(user.getUserPhone()))));
  }

  @Test
  void createUserTest() {
    // given
    User user = User.builder().userEmail(MOCK_EMAIL).build();
    UserDto userDto = UserDto.builder().userEmail(MOCK_EMAIL).build();
    when(userRepository.save(any())).thenReturn(user);

    // when
    UserDto createdUser = userService.createUser(userDto);

    // then
    assertThat(createdUser, hasProperty("userEmail", equalTo(user.getUserEmail())));
  }

  @Test
  void deleteUserTest() {
    // given
    User user = User.builder().userEmail(MOCK_EMAIL).build();
    when(userRepository.findByUserEmail(MOCK_EMAIL)).thenReturn(Optional.of(user));
    doNothing().when(userRepository).delete(user);

    // when
    userService.deleteUser(MOCK_EMAIL);

    // then
    verify(userRepository, times(1)).delete(user);
  }

  @Test
  void deleteUserWithExceptionTest() {
    doThrow(UserNotFoundException.class).when(userRepository).findByUserEmail(MOCK_EMAIL);

    assertThrows(UserNotFoundException.class, () -> userService.deleteUser(MOCK_EMAIL));
  }

  @Test
  void calculateUsersNumberTest() {
    // given
    when(userRepository.calculateUsersNumber()).thenReturn(1);

    // when
    int countUsers = userService.calculateUsersNumber();

    // then
    assertEquals(1, countUsers);
  }
}
