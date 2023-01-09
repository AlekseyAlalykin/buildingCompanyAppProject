package com.example.services;

import com.example.model.User;

public class AuthenticationService {
    public static boolean authenticate(String email, String password){
        User user = UserManager.getInstance().getUserByEmail(email);
        if (user == null)
            return false;

        if (!user.getPassword().equals(password))
            return false;

        return true;
    }
}
