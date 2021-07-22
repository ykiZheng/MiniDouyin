package com.example.minidouyin.API;

import com.google.gson.annotations.SerializedName;

public class UploadResponse {
    @SerializedName("result")
    public POSTMessage message;
    @SerializedName("success")
    public boolean success;
}
