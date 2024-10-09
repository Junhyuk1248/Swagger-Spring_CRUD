package com.spring.practice.simple_project.service;

import com.spring.practice.simple_project.domain.User;
import com.spring.practice.simple_project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<User> createWithList(List<User> userList) {
        List<User> result = new ArrayList<>();

        for(User user : userList){
            User addUser = userRepository.create(user);
            result.add(addUser);
        }
        return result;
    }

    @Override
    public User getOne(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User modifyOne(User user) {
        return userRepository.modify(user);
    }

    @Override
    public String deleteOne(String username) {
        int result = userRepository.delete(username);
        if (result == 1)
            return username;
        return null;
    }

    @Override
    public int login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public String logout() {
        return "execute logout";
    }

    @Override
    public List<User> createWithArray(List<User> userList) {
        List<User> result = new ArrayList<>();

        for(User user : userList){
            User addUser = userRepository.create(user);
            result.add(addUser);
        }
        return result;
    }

    @Override
    public User createOne(User user) {
        return userRepository.create(user);
    }
}
