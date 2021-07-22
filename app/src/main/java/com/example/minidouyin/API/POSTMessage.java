package com.example.minidouyin.API;

import com.google.gson.annotations.SerializedName;

public class POSTMessage {
    // 学号
    @SerializedName("student_id")
    private String studentId;
    //发布者的用户名
    @SerializedName("user_name")
    private String userName;
    //附加信息
    @SerializedName("extra_value")
    private String extraValue;

    //视频源文件地址
    @SerializedName("video_url")
    private String videoUrl;
    //封面图片地址
    @SerializedName("image_url")
    private String imageUrl;



    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getStudentId() {
        return studentId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }

}
