package com.yedona.takephoto.utils;

import android.Manifest;
import android.app.Activity;
import android.os.Build;

import com.yedona.takephoto.callback.BaseCallBack;
import com.yedona.takephoto.callback.PermissionCallback;
import com.yedona.takephoto.config.Config;
import com.yedona.takephoto.config.TakeType;
import com.yedona.takephoto.config.TakeVideoConfig;
import com.yedona.takephoto.config.TakeVideoConfigBuilder;
import com.yedona.takephoto.config.ZoomConfig;
import com.yedona.takephoto.config.ZoomConfigBuilder;
import com.yedona.takephoto.ui.PermissionActivity;

/**
 * @author: yedona
 * @date: 2020/5/7 16:32
 * @description
 */
public class YUtils {

    private YUtils() {

    }


    public static void startTakeVideo(Activity activity, BaseCallBack callBack) {
        startTakeVideo(activity, null, callBack);
    }

    public static void startTakeVideo(Activity activity, TakeVideoConfig config, BaseCallBack callBack) {

        if (config == null) {
            config = new TakeVideoConfigBuilder().createTakeVideoConfig();
        }

        PhotoUtils.startRecord(activity, config, callBack);
    }


    private static void startTake(final Activity activity, final Config config, final BaseCallBack callBack) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //获取运行时权限
            PermissionActivity.start(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionCallback() {
                @Override
                public void onPermissionSuccess() {

                    PhotoUtils.startTake(activity, config, callBack);

                }

                @Override
                public void onPermissionFailure(String[] permission) {
                    callBack.onFailure(0);
                }
            });
        } else {
            PhotoUtils.startTake(activity, config, callBack);
        }
    }


    public static void startForTakePhoto(final Activity activity, final BaseCallBack callBack) {

        startTake(activity, new Config(false, null, TakeType.TAKE_CAMERA_PHOTO, null), callBack);
    }


    public static void startForTakePhotoAndZoom(final Activity activity, ZoomConfig config, final BaseCallBack callBack) {
        if (config == null) {
            config = new ZoomConfigBuilder().createZoomConfig();
        }
        startTake(activity, new Config(false, config, TakeType.TAKE_CAMERA_PHOTO, null), callBack);
    }


    public static void startForPickGalleryPhotoAndZoom(Activity activity, BaseCallBack callBack) {
        startForPickGalleryPhotoAndZoom(activity, null, callBack);
    }

    public static void startForPickGalleryPhotoAndZoom(Activity activity, ZoomConfig config, BaseCallBack callBack) {


        if (config == null) {
            config = new ZoomConfigBuilder().createZoomConfig();
        }
        start(activity, new Config(true, config, TakeType.PICK_GALLERY_PHOTO, null), callBack);
    }


    public static void startForPickGalleryPhoto(Activity activity, BaseCallBack callBack) {
        start(activity, new Config(false, null, TakeType.PICK_GALLERY_PHOTO, null), callBack);
    }

    public static void startForPickGalleryVideo(Activity activity, BaseCallBack callBack) {
        start(activity, new Config(false, null, TakeType.PICK_GALLERY_VIDEO, null), callBack);
    }

    public static void startForPickGalleryPhotoVideo(Activity activity, BaseCallBack callBack) {
        start(activity, new Config(false, null, TakeType.PICK_GALLERY_PHOTO_VIDEO, null), callBack);
    }




    private static void start(final Activity activity, final Config config, final BaseCallBack callBack) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //获取运行时权限
            PermissionActivity.start(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new PermissionCallback() {
                @Override
                public void onPermissionSuccess() {
                    PhotoUtils.startPick(activity, config, callBack);
                }

                @Override
                public void onPermissionFailure(String[] permission) {
                    callBack.onFailure(0);
                }
            });
        } else {
            PhotoUtils.startPick(activity, config, callBack);
        }
    }


}
