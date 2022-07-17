package com.epam.spring.web.mvc.conferences.controller;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

  private static final String MOCK_EMAIL = "speaker@gmail.com";
  private static final String MOCK_PHONE = "+123456789";
  private static final int MOCK_ROLE_ID = 1;
  private static final String MOCK_ADDRESS = "Lviv";
  private static final String MOCK_UPDATED_EMAIL = "moderator@gmail.com";
  private static final String MOCK_UPDATED_PHONE = "+987654321";

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockBean private UserService userService;

  @Test
  void getUserTest() throws Exception {
    UserDto userDto = UserDto.builder().userEmail(MOCK_EMAIL).build();

    when(userService.getUser(MOCK_EMAIL)).thenReturn(userDto);

    mockMvc
        .perform(get("/users/" + MOCK_EMAIL))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.userEmail").value(userDto.getUserEmail()));
  }

  @Test
  void createUserTest() throws Exception {
    UserDto userDto = UserDto.builder().userEmail(MOCK_EMAIL).userPhone(MOCK_PHONE).build();

    when(userService.createUser(userDto)).thenReturn(userDto);

    mockMvc
        .perform(
            post("/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDto)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.userEmail").value(userDto.getUserEmail()));

    verify(userService, times(1)).createUser(userDto);
  }

  @Test
  void updateUserTest() throws Exception {
    UserDto userDto = UserDto.builder().userEmail(MOCK_EMAIL).userPhone(MOCK_PHONE).build();

    when(userService.updateUser(MOCK_EMAIL, userDto)).thenReturn(userDto);

    mockMvc
        .perform(
            put("/users/" + MOCK_EMAIL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDto)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.userEmail").value(userDto.getUserEmail()));

    verify(userService, times(1)).updateUser(MOCK_EMAIL, userDto);
  }

  @Test
  void deleteUserTest() throws Exception {
    doNothing().when(userService).deleteUser(MOCK_EMAIL);

    mockMvc
        .perform(delete("/users/" + MOCK_EMAIL))
        .andDo(print())
        .andExpect(status().isNoContent());

    verify(userService, times(1)).deleteUser(MOCK_EMAIL);
  }

  @Test
  void calculateUsersNumberTest() throws Exception {
    when(userService.calculateUsersNumber()).thenReturn(0);

    mockMvc
        .perform(get("/users/count"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
  }

  @Test
  void getUsersByRoleIdTest() throws Exception {
    UserDto userDto = UserDto.builder().userEmail(MOCK_EMAIL).roleId(MOCK_ROLE_ID).build();
    List<UserDto> userDtoList = new ArrayList<>();
    userDtoList.add(userDto);

    when(userService.getUsersByRoleId(1)).thenReturn(userDtoList);

    mockMvc
        .perform(get("/users/getByRole/{roleId}", MOCK_ROLE_ID))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].userEmail").value(userDto.getUserEmail()));
  }

  @Test
  void getUsersByUserAddressTest() throws Exception {
    UserDto userDto = UserDto.builder().userEmail(MOCK_EMAIL).userAddress(MOCK_ADDRESS).build();
    List<UserDto> userDtoList = new ArrayList<>();
    userDtoList.add(userDto);

    when(userService.getUsersByUserAddress(MOCK_ADDRESS, 0, 3)).thenReturn(userDtoList);

    mockMvc
        .perform(get("/users/getByAddress/" + MOCK_ADDRESS))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].userEmail").value(userDto.getUserEmail()));
  }
}
