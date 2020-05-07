package com.yedona.takephoto.config;

/**
 * @author: yedona
 * @date: 2020/4/15 17:22
 * @description 启动类型
 */
public class TakeType {

    /**
     * 从系统相册中选取图片
     */
    public static final int PICK_GALLERY_PHOTO = 0;

    /**
     * 从系统相册中选取视频
     */
    public static final int PICK_GALLERY_VIDEO = 1;

    /**
     * 从系统相册中选取视频和图片
     */
    public static final int PICK_GALLERY_PHOTO_VIDEO = 2;

    /**
     * 拍照获取图片
     */
    public static final int TAKE_CAMERA_PHOTO = 3;


    /**
     * 拍摄获取视频
     */
    public static final int TAKE_CAMERA_VIDEO = 4;

}
