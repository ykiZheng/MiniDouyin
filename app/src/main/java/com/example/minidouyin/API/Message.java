package com.example.minidouyin.API;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("_id")
    public String Id;
    @SerializedName("student_id")
    public String studentId;
    @SerializedName("user_name")
    public String user_name;
    @SerializedName("extra_value")
    public String extra_value;
    @SerializedName("image_url")
    public String image_url;
    @SerializedName("video_url")
    public String video_url;
    @SerializedName("image_w")
    public String image_w;
    @SerializedName("image_h")
    public String image_h;
    @SerializedName("createdAt")
    public String createdAt;
    @SerializedName("updatedAt")
    public String updatedAt;



    public void setId(String Id) {
        this.Id = Id;
    }
    public String getId() {
        return Id;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getStudentId() {
        return studentId;
    }

    public void setUsername(String user_name) {
        this.user_name = user_name;
    }
    public String getUsername() {
        return user_name;
    }

    public void setExtravalue(String extra_value) {
        this.extra_value = extra_value;
    }
    public String getExtravalue() {
        return extra_value;
    }

    public void setCoverimage(String image_url) { this.image_url = image_url; }
    public String getCoverimage() { return image_url; }

    public void setVideo(String video_url) { this.video_url = video_url; }
    public String getVideo() { return video_url; }

}
