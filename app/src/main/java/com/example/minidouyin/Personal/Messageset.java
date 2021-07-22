package com.example.minidouyin.Personal;

import com.example.minidouyin.API.Message;

import java.util.ArrayList;
import java.util.List;

public class Messageset {
    public static List<Message> messageset = new ArrayList<>();

    public static int size(){
        return messageset.size();
    }

    public static void addMessage(Message message){
        messageset.add(message);
    }

}
