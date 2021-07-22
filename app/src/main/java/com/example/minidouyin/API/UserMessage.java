package com.example.minidouyin.API;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserMessage implements Serializable {

    public String Id;
    public String studentId;
    public String user_name;
    public String extra_value;
    public String image_url;
    public String video_url;
    public String image_w;
    public String image_h;
    public String createdAt;
    public String updatedAt;

    public UserMessage(Message message){
        this.Id = message.Id;
        this.studentId = message.studentId;
        this.user_name = message.user_name;
        this.extra_value = message.extra_value;
        this.image_url = message.image_url;
        this.video_url = message.video_url;
        this.image_w = message.image_w;
        this.image_h = message.image_h;
        this.createdAt = message.createdAt;
        this.updatedAt = message.updatedAt;
    }

    public Message getmessage(){
        Message message = new Message();
        message.Id = this.Id;
        message.user_name = this.user_name;
        message.extra_value = this.extra_value;
        message.image_url = this.image_url;
        message.video_url = this.video_url;
        message.image_w = this.image_w;
        message.image_h = this.image_h;
        message.createdAt = this.createdAt;
        message.updatedAt = this.updatedAt;
        return message;
    }
}
