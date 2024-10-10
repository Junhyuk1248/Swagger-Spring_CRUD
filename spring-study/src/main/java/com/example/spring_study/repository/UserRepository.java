package com.example.spring_study.repository;

import com.example.spring_study.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findWithList();
    Optional<User> findByName(String userName);
    Optional<User> deleteUser(String userName);
    User createUser(User user);
    List<User> createWithList(List<User> users);
    String login(String userName, String password);
}
