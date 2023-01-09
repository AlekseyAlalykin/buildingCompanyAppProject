package com.example.services;

import com.example.model.Message;

import java.util.LinkedList;
import java.util.List;

public class MessageService {
    static private MessageService messageService;

    private List<Message> messages;

    private MessageService(){
        this.messages = new LinkedList<>();
    }

    public static MessageService getInstance(){
        if (messageService == null)
            messageService = new MessageService();

        return messageService;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(String message, boolean isSelfSend){
        messages.add(new Message(message,isSelfSend));
    }

    public void addMessage(Message message){
        messages.add(message);
    }
}
