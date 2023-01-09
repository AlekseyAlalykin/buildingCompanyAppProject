package com.example.model;

public class Order {
    public static final int PROCESSING = 0;
    public static final int IN_PROGRESS = 1;
    public static final int FINISHED = 2;
    public static final int CANCELED = 3;

    private String date;
    private String title;
    private Float rating;
    private Integer currentState;

    public Order(String date, String title, Float rating, Integer currentState) {
        this.date = date;
        this.title = title;
        this.rating = rating;
        this.currentState = currentState;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Integer currentState) {
        this.currentState = currentState;
    }
}
