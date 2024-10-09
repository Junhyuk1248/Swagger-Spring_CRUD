package com.spring.practice.simple_project.repository;

import com.spring.practice.simple_project.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private static Map<Long, User> userDB = new HashMap<>();
    private static Long sequence = 0L;
    @Override
    public User create(User user) {
        user.setId(sequence);
        userDB.put(sequence++, user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User findUser = null;
        for(Map.Entry<Long, User> entry : userDB.entrySet()){
            findUser = entry.getValue();
            if (findUser.getUsername().equals(username)){
                break;
            }
        }
        return findUser;
    }

    @Override
    public User modify(User user) {
        Long findId = user.getId();
        userDB.put(findId, user);
        User findUser = userDB.get(findId);
        return findUser;
    }

    @Override
    public int delete(String username) {
        User findUser = null;
        Long findId = null;
        for(Map.Entry<Long, User> entry : userDB.entrySet()){
            findUser = entry.getValue();
            if (findUser.getUsername().equals(username)){
                findId = findUser.getId();
                break;
            }
        }
        if (findId == null){
            return 0;
        }
        userDB.remove(findId);
        return 1;
    }

    @Override
    public int findByUsernameAndPassword(String username, String password) {
        int result = 0;
        for(Map.Entry<Long, User> entry : userDB.entrySet()){
            User findUser = entry.getValue();
            if (findUser.getUsername().equals(username) && findUser.getPassword().equals(password)){
                result = 1;
                break;
            }
        }
        return result;
    }
}
