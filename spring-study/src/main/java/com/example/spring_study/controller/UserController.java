package com.example.spring_study.controller;

import com.example.spring_study.domain.User;
import com.example.spring_study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody User user){

        User join = userService.join(user);
        System.out.println("join = " + join);
        
        return ResponseEntity.status(200).body(join);
    }

    @PostMapping("/user/findWithList")
    public ResponseEntity<List<User>> findWithList(){

        List<User> withList = userService.findWithList();
        System.out.println("withList = " + withList);
        return ResponseEntity.status(200).body(withList);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity findByName(@PathVariable("username") String username){

        Optional<User> byName = userService.findByName(username);
        System.out.println("byName = " + byName);

        return ResponseEntity.status(200).body(byName);
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity deleteByName(@PathVariable("username") String username){

        Optional<User> delete = userService.deleteByName(username);

        return ResponseEntity.status(200).body(delete);
    }

    @PostMapping("/user/createWithList")
    public ResponseEntity<List<User>> createWithList(@RequestBody List<User> user){

        List<User> result = userService.createWithList(user);

        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/user/login")
    public ResponseEntity<String> login(@RequestParam("userName") String userName, @RequestParam("password") String password){
        String result = userService.login(userName, password);

        return ResponseEntity.status(200).body(result);
    }
}
