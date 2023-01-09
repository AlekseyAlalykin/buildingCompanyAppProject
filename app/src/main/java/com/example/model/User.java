package com.example.model;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class User {
    private String email;

    private String phoneNumber;

    private String password;

    private List<Order> orders;

    public User(String email, String phoneNumber, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;

        orders = new LinkedList<>();

        orders.add(new Order("2022-11-25","Установка окон",4f, Order.FINISHED));
        orders.add(new Order("2022-12-26","Ремонт балкона",0f, Order.IN_PROGRESS));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof User))
            return false;

        if (this.email.equals(((User) obj).getEmail()))
            return true;

        return false;
    }
}
