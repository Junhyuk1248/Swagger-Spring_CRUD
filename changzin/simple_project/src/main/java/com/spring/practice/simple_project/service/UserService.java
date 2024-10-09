package com.spring.practice.simple_project.service;

import com.spring.practice.simple_project.domain.User;
import java.util.List;

public interface UserService {
    List<User> createWithList(List<User> userList);
    User getOne(String username);
    User modifyOne(User user);
    String deleteOne(String username);
    int login(String username, String password);
    String logout();
    List<User> createWithArray(List<User> userList);
    User createOne(User user);
}
