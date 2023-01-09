package com.example.model;

public class Message {
    private String message;
    private boolean isSelfSend;

    public Message(String message, boolean isSelfSend){
        this.message = message;
        this.isSelfSend = isSelfSend;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSelfSend() {
        return isSelfSend;
    }

    public void setSelfSend(boolean selfSend) {
        isSelfSend = selfSend;
    }
}
