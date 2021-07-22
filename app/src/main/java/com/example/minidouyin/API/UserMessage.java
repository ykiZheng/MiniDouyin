package com.example.minidouyin.API;

import java.io.Serializable;

public class UserMessage implements Serializable {

    public Message message;

    public UserMessage(Message message){
        this.message = message;
    }
}
