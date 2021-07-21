package com.example.minidouyin;

public class VideoInfo {
    private String name;
    private int ImageId;

    public VideoInfo(String name,int ImageId){
        this.name = name;
        this.ImageId = ImageId;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
