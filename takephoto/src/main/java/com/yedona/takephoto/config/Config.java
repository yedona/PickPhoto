package com.yedona.takephoto.config;

/**
 * @author: yedona
 * @date: 2020/4/2 18:22
 * @description
 */
public class Config {


    /**
     * 是否去缩放
     */
    private boolean isStartZoom;

    /**
     * 缩放用的参数
     */
    private ZoomConfig config;


    /**
     * 选择的类型
     */
    private int type = TakeType.PICK_GALLERY_PHOTO;


    private TakeVideoConfig takeVideoConfig;


    public Config(boolean isStartZoom, ZoomConfig config, int type, TakeVideoConfig takeVideoConfig) {
        this.isStartZoom = isStartZoom;
        this.config = config;
        this.type = type;
        this.takeVideoConfig = takeVideoConfig;
    }

    public boolean isStartZoom() {
        return isStartZoom;
    }

    public void setStartZoom(boolean startZoom) {
        isStartZoom = startZoom;
    }

    public ZoomConfig getZoomConfig() {
        return config;
    }

    public void setZoomConfig(ZoomConfig config) {
        this.config = config;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public TakeVideoConfig getTakeVideoConfig() {
        return takeVideoConfig;
    }

    public void setTakeVideoConfig(TakeVideoConfig takeVideoConfig) {
        this.takeVideoConfig = takeVideoConfig;
    }
}
