package com.yedona.takephoto.config;

public class ZoomConfigBuilder {
    private String outputPath;
    private int aspectX=1;
    private int aspectY=1;
    private int outputX=200;
    private int outputY=200;

    public ZoomConfigBuilder setOutputPath(String outputPath) {
        this.outputPath = outputPath;
        return this;
    }

    public ZoomConfigBuilder setAspectX(int aspectX) {
        this.aspectX = aspectX;
        return this;
    }

    public ZoomConfigBuilder setAspectY(int aspectY) {
        this.aspectY = aspectY;
        return this;
    }

    public ZoomConfigBuilder setOutputX(int outputX) {
        this.outputX = outputX;
        return this;
    }

    public ZoomConfigBuilder setOutputY(int outputY) {
        this.outputY = outputY;
        return this;
    }

    public ZoomConfig createZoomConfig() {
        return new ZoomConfig(outputPath, aspectX, aspectY, outputX, outputY);
    }
}