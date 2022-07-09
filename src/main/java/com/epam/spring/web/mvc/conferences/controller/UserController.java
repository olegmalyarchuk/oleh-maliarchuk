package com.epam.spring.web.mvc.conferences.controller;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.service.UserService;
import com.epam.spring.web.mvc.conferences.validation.BasicInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(value = "conferences", description = "Operations related to users in Conference system")
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{email}")
    @ApiOperation(value = "Get user by email")
    public UserDto getUser(@PathVariable String email) {
        log.info("Getting user with email{}", email);
        return userService.getUser(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Create new user")
    public UserDto createUser(@Validated(BasicInfo.class)  @RequestBody UserDto userDto) {
        log.info("creating user{}", userDto);
        return userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{email}")
    @ApiOperation(value = "Update user by email")
    public UserDto updateUser(@PathVariable String email, @RequestBody UserDto userDto) {
        log.info("Updating user with email{}", email);
        return userService.updateUser(email, userDto);
    }

    @DeleteMapping(value = "/{email}")
    @ApiOperation(value = "Delete user by email")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        log.info("Deleting user with email{}", email);
        userService.deleteUser(email);
        return  ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/count")
    @ApiOperation(value = "Show count of users")
    public int calculateUsersNumber() {
        log.info("Calculating users number{}", userService.calculateUsersNumber());
        return userService.calculateUsersNumber();
    }


    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all users by role")
    @GetMapping(value = "/getByRole/{email}")
    public List<UserDto> getUsersByRoleId(@PathVariable int role_id) {
        log.info("Getting users by role_id {}", role_id);
        return userService.getUsersByRoleId(role_id);
    }

}
