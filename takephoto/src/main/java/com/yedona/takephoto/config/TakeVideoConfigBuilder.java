package com.yedona.takephoto.config;

public class TakeVideoConfigBuilder {
    private String videoPath;
    private float quality = 1;
    private int length = 5;

    public TakeVideoConfigBuilder setVideoPath(String videoPath) {
        this.videoPath = videoPath;
        return this;
    }

    public TakeVideoConfigBuilder setQuality(float quality) {
        this.quality = quality;
        return this;
    }

    public TakeVideoConfigBuilder setLength(int length) {
        this.length = length;
        return this;
    }

    public TakeVideoConfig createTakeVideoConfig() {
        return new TakeVideoConfig(videoPath, quality, length);
    }
}