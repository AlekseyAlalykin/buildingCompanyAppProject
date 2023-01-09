package com.example.services;

import com.example.model.User;

import java.util.LinkedList;
import java.util.List;

public class UserManager {
    static private UserManager instance;

    static {
        getInstance();
    }

    private List<User> users;

    private UserManager(){
        users = new LinkedList<>();

        users.add(new User("test@test.com","88005553535","password"));
    }

    public static UserManager getInstance(){
        if (instance == null)
            instance = new UserManager();

        return instance;
    }

    public void addUser(User user){
        if (users.contains(user))
            throw new IllegalStateException();

        users.add(user);
    }

    public User getUserByEmail(String email){
        for (User user: users){
            if (user.getEmail().equals(email))
                return user;
        }

        return null;
    }

    public boolean existsByEmail(String email){
        for (User user: users)
            if (user.getEmail().equals(email))
                return true;

        return false;
    }

    public boolean existsByPhone(String phone){
        for (User user: users)
            if (user.getPhoneNumber().equals(phone))
                return true;

        return false;
    }
}
