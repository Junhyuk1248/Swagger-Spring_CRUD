package com.user.user.rb.controller;

import com.user.user.rb.DTO.ApiResponse;
import com.user.user.rb.domain.User;
import com.user.user.rb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    //userSerivce 객체를 선언하고 userService에 등록된 비즈니스 로직을 사용할 수 있도록한다.

    @PostMapping("/createWithList")
    public ResponseEntity<ApiResponse>createUser(@RequestBody List<User> users){

        ApiResponse response = userService.saveUser(users);
        log.debug("debug={}",response);
        return new ResponseEntity<>(response,HttpStatus.valueOf(response.getCode()));
    }


   //한 객체 상태로 포스트 매핑으로 user 를 등록한다.
    /*@PostMapping
    public ResponseEntity<ApiResponse>createUser(@RequestBody User user){
        ApiResponse response = userService.saveUser(user);
        log.debug("trace = {}",response);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        //RequestBody에 요청할 객체 User (User에 User도메인에 등록된 값들을 보낼거다)
        //User 객체를 참조하는 savedUser 변수는 userService에 있는 saveUser로직을 사용하여 user를 등록한다.
        //등록이되면 반환값으로 User값을 보내준다.
    }

     */

    @GetMapping("/{username}")
    public ResponseEntity<?>findByUsername(@PathVariable String username){
        Optional<User> getUser  = userService.findByUsername(username);
        log.debug("debug={}",getUser);
        return getUser.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<?>deleteByUsername(@PathVariable String username){
        boolean isDeleted = userService.deleteByName(username);
        log.debug("debug={}",isDeleted);
        return ResponseEntity.ok().body("유저가 삭제되었습니다.");

    }
    /*
    @DeleteMapping("/{username}")
    public ResponseEntity<?>deleteByUsername(@PathVariable String username){
        List<User> deleteUser = userService.deleteUser(username);
        log.debug("debug={}",deleteUser);
        return deleteUser.get(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

     */


//    @DeleteMapping("/{username}")
//    public ResponseEntity<ApiResponse>deleteUser(@PathVariable(name="username") String username){
//        ApiResponse delete = userService.deleteUser(username);
//        log.debug("debug log={}",delete);
//        return  new ResponseEntity<>(delete,HttpStatus.valueOf(delete.getCode()));
//    }


}
