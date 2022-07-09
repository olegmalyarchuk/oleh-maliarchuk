package com.epam.spring.web.mvc.conferences.persistence.dao;

import com.epam.spring.web.mvc.conferences.persistence.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(nativeQuery = true, value = "SELECT user_id as ROWCOUNT FROM users order by user_id desc limit 1")
    int calculateUsersNumber();


    List<User> findAllByRoleId(int role_id);

    Optional<User> findByUserEmail(String user_email);

    List<User> findAllByUserAddress(String address, Pageable pageable);
}
