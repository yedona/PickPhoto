package com.yedona.takephoto.config;

public class TakeVideoConfig {


    private String videoPath;

    private float quality=1;

    private int length=10;

    public TakeVideoConfig(String videoPath, float quality, int length) {
        this.videoPath = videoPath;
        this.quality = quality;
        this.length = length;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public float getQuality() {
        return quality;
    }

    public void setQuality(float quality) {
        this.quality = quality;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
