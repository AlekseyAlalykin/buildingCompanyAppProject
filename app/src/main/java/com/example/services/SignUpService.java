package com.example.services;

import com.example.model.User;

public class SignUpService {
    public void SingUpUser(String phoneNumber, String email, String password){
        if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"))
            throw new IllegalArgumentException();

        if (!phoneNumber.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$"))
            throw new IllegalArgumentException();

        if (password.length() < 8)
            throw new IllegalArgumentException();

        UserManager userManager = UserManager.getInstance();
        userManager.addUser(new User(email,phoneNumber,password));
    }
}
