package com.example.spring_study.repository;

import com.example.spring_study.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository{

    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public List<User> findWithList() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<User> findByName(String userName) {
        return store.values().stream()
                .filter(user -> user.getUserName().equals(userName))
                .findAny();
    }

    @Override
    public Optional<User> deleteUser(String userName) {
        Optional<User> result = store.values().stream()
                .filter(user -> user.getUserName().equals(userName))
                .findAny();

        store.remove(result.get().getId());
        return Optional.ofNullable(store.remove(result.get().getId()));
    }

    @Override
    public User createUser(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> createWithList(List<User> users) {

        for (User user : users){
            user.setId(++sequence);
            store.put(user.getId(), user);
        }

        return users;
    }

    @Override
    public String login(String userName, String password) {

        Optional<User> userId = store.values().stream()
                .filter(user -> user.getUserName().equals(userName))
                .findAny();

        if(userId.isPresent()) {
            String userPassword = userId.get().getPassword();
            if (userPassword.equals(password)) {
                return "Login Success";
            }else {
                return "Invalid Password";
            }
        }else{
            return "User Id does not exist";
        }
    }
}
