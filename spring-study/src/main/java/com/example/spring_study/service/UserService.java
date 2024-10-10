package com.example.spring_study.service;

import com.example.spring_study.domain.User;
import com.example.spring_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findWithList(){

        return userRepository.findWithList();
    }

    public Optional<User> findByName(String userName) {
        return userRepository.findByName(userName);
    }

    public Optional<User> deleteByName(String userName){

        return userRepository.deleteUser(userName);
    }

    public User join(User user){
        userRepository.createUser(user);

        return user;
    }

    public List<User> createWithList(List<User> user){

        return userRepository.createWithList(user);
    }

    public String login(String userName, String password){

        return userRepository.login(userName, password);
    }
}
