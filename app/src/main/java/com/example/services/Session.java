package com.example.services;

import com.example.model.User;

public class Session {
    private static User currentUser;

    public static User getCurrentUser(){
        return currentUser;
    }

    public static void setCurrentUser(User user){
        Session.currentUser = user;
    }
}
