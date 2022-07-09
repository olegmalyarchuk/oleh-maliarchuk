package com.epam.spring.web.mvc.conferences.persistence.dao;

import com.epam.spring.web.mvc.conferences.persistence.model.User;

import java.util.List;

public interface UserRepository {
    User getUser(String email);

    User createUser(User user);

    User updateUser(String email, User user);

    void deleteUser(String email);

    int calculateUsersNumber();

    List<User> getUsersByRoleId(int role_id);
}
