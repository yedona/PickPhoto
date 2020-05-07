package com.yedona.takephoto.config;

public class ConfigBuilder {
    private boolean isStartZoom;
    private ZoomConfig config;
    private int type;
    private TakeVideoConfig takeVideoConfig;

    public ConfigBuilder setIsStartZoom(boolean isStartZoom) {
        this.isStartZoom = isStartZoom;
        return this;
    }

    public ConfigBuilder setConfig(ZoomConfig config) {
        this.config = config;
        return this;
    }

    public ConfigBuilder setType(int type) {
        this.type = type;
        return this;
    }

    public ConfigBuilder setTakeVideoConfig(TakeVideoConfig takeVideoConfig) {
        this.takeVideoConfig = takeVideoConfig;
        return this;
    }

    public Config createConfig() {
        return new Config(isStartZoom, config, type, takeVideoConfig);
    }
}