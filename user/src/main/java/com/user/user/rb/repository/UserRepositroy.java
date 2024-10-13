package com.user.user.rb.repository;

import com.user.user.rb.DTO.ApiResponse;
import com.user.user.rb.domain.User;

import java.util.Optional;
//교재를 따라가지말고 내 User객체에 맞게 진행
public interface UserRepositroy {
//    ApiResponse save(User user);
    //user 를 리스트로 받아내야한다.
    ApiResponse save(User user);
    Optional<User> findById(int id);
    //Optional<User> findByName(String username);



    Optional<User> findByName(String username);

//    List<User> findAll();
    boolean deleteByName(String username);
// Repository 자체는 건들필요가 없음 -> 리스트 제작은 userService에서 구현
}
