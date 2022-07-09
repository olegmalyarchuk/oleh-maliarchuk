package com.epam.spring.web.mvc.conferences.persistence.dao.impl;

import com.epam.spring.web.mvc.conferences.exception.UserNotFoundException;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import com.epam.spring.web.mvc.conferences.persistence.dao.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserRepositoryImpl implements UserRepository {
    private final List<User> list = new ArrayList<>();

    @Override
    public User getUser(String email) {
        log.info("Got User by email: " + email);
        return list.stream().filter(user -> user.getUser_email().equals(email))
                    .findFirst().orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User createUser(User user) {
        list.add(user);
        log.info("Created User " + user);
        return user;
    }

    @Override
    public User updateUser(String email, User user) {
        boolean isDeleted = list.removeIf(u -> u.getUser_email().equals(email));
        if (isDeleted) {
            list.add(user);
        } else {
            throw new UserNotFoundException();
        }
        log.info("User with email " + email + " was updated");
        return user;
    }

    @Override
    public void deleteUser(String email) {
        list.removeIf(user -> user.getUser_email().equals(email));
        log.info("User with email {} was deleted", email);
    }

    @Override
    public int calculateUsersNumber() {
        log.info("Users number{}", list.size());
        return list.size();
    }

    @Override
    public List<User> getUsersByRoleId(int role_id) {
            log.info("Got Users by role_id: " + role_id);
            return list.stream().filter(user -> user.getRole_id()==role_id).collect(Collectors.toList());
    }
}
