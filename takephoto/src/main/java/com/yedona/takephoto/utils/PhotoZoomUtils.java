package com.yedona.takephoto.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.yedona.takephoto.avoidresult.AvoidOnResult;
import com.yedona.takephoto.callback.BaseCallBack;
import com.yedona.takephoto.config.ZoomConfig;

import java.io.File;
import java.io.IOException;

public class PhotoZoomUtils {

    /**
     * 调用系统裁剪功能
     */
    public static void startForPhotoZoom(final Activity context, final ZoomConfig config, final BaseCallBack callBack, String originFilePath) {


        boolean isOutPut = !TextUtils.isEmpty(config.getOutputPath());
        Uri outputUri = null;

        if (isOutPut) {
            File file = new File(config.getOutputPath());
            if (!file.exists()) {
                try {
                    file.getParentFile().mkdirs();

                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            outputUri = Uri.fromFile(new File(config.getOutputPath()));
        } else {
            String outputPath = originFilePath.substring(0, originFilePath.lastIndexOf(File.separator)) + File.separator + System.currentTimeMillis() + ".jpg";
            outputUri = Uri.fromFile(new File(outputPath));
            config.setOutputPath(outputPath);
        }


        Uri uri = UriUtils.getUriFromFile(context, originFilePath);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", config.getAspectX());
        intent.putExtra("aspectY", config.getAspectY());
        intent.putExtra("outputX", config.getOutputX());
        intent.putExtra("outputY", config.getOutputY());
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        final String finalOriginFilePath = originFilePath;
        new AvoidOnResult(context)
                .startForResult(intent, new AvoidOnResult.Callback() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        String path;
                        if (data == null || data.getData() == null) {
                            path = config.getOutputPath();
                        } else {
                            path = UriUtils.getRealFilePath(context, data.getData());
                        }
                        callBack.onZoomPhotoSuccess(finalOriginFilePath, path);
                    }
                });

    }
}
