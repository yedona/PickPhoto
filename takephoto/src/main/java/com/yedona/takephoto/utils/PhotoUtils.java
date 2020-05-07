package com.yedona.takephoto.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.yedona.takephoto.avoidresult.AvoidOnResult;
import com.yedona.takephoto.callback.BaseCallBack;
import com.yedona.takephoto.config.Config;
import com.yedona.takephoto.config.TakeType;
import com.yedona.takephoto.config.TakeVideoConfig;

import java.io.File;
import java.io.IOException;

public class PhotoUtils {

    public static void startPick(final Activity activity, final Config config, final BaseCallBack callBack) {


        String uriType = null;

        if (config.getType() == TakeType.PICK_GALLERY_PHOTO_VIDEO) {
            uriType = "video/*,image/*";
        }

        if (config.getType() == TakeType.PICK_GALLERY_PHOTO) {
            uriType = "image/*";
        }

        if (config.getType() == TakeType.PICK_GALLERY_VIDEO) {
            uriType = "video/*";
        }

        Intent pickIntent = new Intent(Intent.ACTION_PICK);
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, uriType);
        new AvoidOnResult(activity)
                .startForResult(pickIntent, new AvoidOnResult.Callback() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        if (resultCode == Activity.RESULT_OK) {
                            Uri uri = data.getData();
                            String imgPath = UriUtils.getPath(activity, uri);
                            if (config.getZoomConfig() != null && config.getType() == TakeType.PICK_GALLERY_PHOTO) {

                                PhotoZoomUtils.startForPhotoZoom(activity, config.getZoomConfig(), callBack, imgPath);
                                callBack.onSuccess(imgPath);
                            } else {
                                callBack.onSuccess(imgPath);
                            }
                        } else {
                            callBack.onFailure(1);
                        }
                    }
                });

    }


    public static void startTake(final Activity activity, final Config config, final BaseCallBack callBack) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        final String path = "/storage/emulated/0" + "/" + System.currentTimeMillis() + ".jpg";
        try {
            new File(path).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Uri uri = UriUtils.getUriFromFile(activity, path);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        new AvoidOnResult(activity)
                .startForResult(intent, new AvoidOnResult.Callback() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {


                        if (resultCode == Activity.RESULT_OK) {
                            if (config.getZoomConfig() != null && config.getType() == TakeType.PICK_GALLERY_PHOTO) {

                                PhotoZoomUtils.startForPhotoZoom(activity, config.getZoomConfig(), callBack, path);
                                callBack.onSuccess(path);
                            } else {
                                callBack.onSuccess(path);
                            }
                        } else {
                            callBack.onFailure(1);
                        }
                    }
                });
    }

    public static void startRecord(Activity activity, final TakeVideoConfig config, final BaseCallBack callBack) {


        if (TextUtils.isEmpty(config.getVideoPath())) {
            final String path = "/storage/emulated/0" + "/" + System.currentTimeMillis() + ".mp4";
            config.setVideoPath(path);
        }

        File file = new File(config.getVideoPath());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Uri uri = UriUtils.getUriFromFile(activity, config.getVideoPath());
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, config.getQuality());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, config.getLength());

        new AvoidOnResult(activity)
                .startForResult(intent, new AvoidOnResult.Callback() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {

                        if (resultCode == Activity.RESULT_OK) {
                            callBack.onSuccess(config.getVideoPath());

                        } else {
                            callBack.onFailure(1);
                        }

                    }
                });


    }


}
