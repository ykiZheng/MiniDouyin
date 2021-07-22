package com.example.minidouyin.newtwork.model;

import com.google.gson.annotations.SerializedName;

public class GetVideosResponse {
    @SerializedName("feeds")
    public Message message;
    @SerializedName("success")
    public boolean success;
}
