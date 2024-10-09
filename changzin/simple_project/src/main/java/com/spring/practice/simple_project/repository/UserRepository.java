package com.spring.practice.simple_project.repository;

import com.spring.practice.simple_project.domain.User;

public interface UserRepository {
    User create(User user);
    User findByUsername(String username);
    User modify(User user);
    int delete(String username);
    int findByUsernameAndPassword(String username, String password);
}
