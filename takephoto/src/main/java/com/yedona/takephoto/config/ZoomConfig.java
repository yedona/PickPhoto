package com.yedona.takephoto.config;

public class ZoomConfig {



    /**
     * 压缩之后的输出文件路径
     */
    private String outputPath;

    //缩放宽高的比例
    private int aspectX = 1;

    private int aspectY = 1;

    //输出的宽高
    private int outputX = 200;

    private int outputY = 200;

    public ZoomConfig(String outputPath, int aspectX, int aspectY, int outputX, int outputY) {
        this.outputPath = outputPath;
        this.aspectX = aspectX;
        this.aspectY = aspectY;
        this.outputX = outputX;
        this.outputY = outputY;
    }


    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public int getAspectX() {
        return aspectX;
    }

    public void setAspectX(int aspectX) {
        this.aspectX = aspectX;
    }

    public int getAspectY() {
        return aspectY;
    }

    public void setAspectY(int aspectY) {
        this.aspectY = aspectY;
    }

    public int getOutputX() {
        return outputX;
    }

    public void setOutputX(int outputX) {
        this.outputX = outputX;
    }

    public int getOutputY() {
        return outputY;
    }

    public void setOutputY(int outputY) {
        this.outputY = outputY;
    }
}
