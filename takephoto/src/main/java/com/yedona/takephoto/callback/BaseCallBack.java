package com.yedona.takephoto.callback;

public abstract class BaseCallBack {


    public abstract void onSuccess(String path);

    public abstract void onFailure(int errCode);


    /**
     * @param path 原图路径
     * @param zoomPath 裁剪后的图片的路径
     */
    public void onZoomPhotoSuccess(String path, String zoomPath) {


    }


}
