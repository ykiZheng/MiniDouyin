package com.example.minidouyin;

public class VideoInfo {
    private String name;
    private String videoUrl;

    public VideoInfo(String name,String videoUrl){
        this.name = name;
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        videoUrl = videoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
