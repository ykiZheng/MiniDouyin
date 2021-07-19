package com.example.minidouyin.newtwork.model;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Message {
    // 视频标题
    @SerializedName("student_id")
    private String studentId;
    //用户名
    @SerializedName("user_name")
    private String userName;
    //视频
    @SerializedName("video_url")
    private String videoUrl;
    //封面
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("image_w")
    private int imageW;
    @SerializedName("image_h")
    private int imageH;

    @SerializedName("_id")
    private String Id;

    @SerializedName("createdAt")
    private Date createdAt;
    @SerializedName("updatedAt")
    private Date updatedAt;

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

    public void setImageW(int imageW) {
        this.imageW = imageW;
    }
    public int getImageW() {
        return imageW;
    }

    public void setImageH(int imageH) {
        this.imageH = imageH;
    }
    public int getImageH() {
        return imageH;
    }

    public void setCreatedAt(Date createdat) {
        this.createdAt = createdat;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAtt(Date updatedat) {
        this.updatedAt = updatedat;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
}
