package com.spring.practice.simple_project.controller;

import com.spring.practice.simple_project.domain.User;
import com.spring.practice.simple_project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping("/createWithList")
    public ResponseEntity createWithList(@RequestBody List<User> userList){
        List<User> result = userService.createWithList(userList);
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/{username}")
    public ResponseEntity getUser(@PathVariable String username){
        User result = userService.getOne(username);
        return ResponseEntity.status(200).body(result);
    }

    @PutMapping("/{username}")
    public ResponseEntity modify(@PathVariable String username, @RequestBody User user){
        User result = userService.modifyOne(user);
        return ResponseEntity.status(201).body(result);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity delete(@PathVariable String username){
        String result = userService.deleteOne(username);
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/login")
    public ResponseEntity login(String username, String password){
        int result = userService.login(username, password);
        if (result == 0){
            return ResponseEntity.status(404).body("Cannot find User");
        }
        return ResponseEntity.status(200).body("login success");
    }

    @GetMapping("/logout")
    public ResponseEntity logout(){
        String result = userService.logout();
        return ResponseEntity.status(200).body(result);
    }

    @PostMapping("/createWithArray")
    public ResponseEntity createWithArray(@RequestBody List<User> userList){
        List<User> result = userService.createWithList(userList);
        return ResponseEntity.status(201).body(result);
    }

    @PostMapping("")
    public ResponseEntity join(@RequestBody User user){
        User result = userService.createOne(user);
        return ResponseEntity.status(201).body(result);
    }
}
