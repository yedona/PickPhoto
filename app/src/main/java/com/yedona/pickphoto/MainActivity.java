package com.yedona.pickphoto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yedona.takephoto.callback.BaseCallBack;
import com.yedona.takephoto.config.TakeVideoConfigBuilder;
import com.yedona.takephoto.config.ZoomConfig;
import com.yedona.takephoto.config.ZoomConfigBuilder;
import com.yedona.takephoto.utils.YUtils;

public class MainActivity extends AppCompatActivity {


    private BaseCallBack callBack = new BaseCallBack() {
        @Override
        public void onSuccess(String path) {
            Log.d("yedona", "onSuccess: " + path);
            Glide.with(MainActivity.this).load(path).into(iv);
        }

        @Override
        public void onFailure(int errCode) {


        }

        @Override
        public void onZoomPhotoSuccess(String path, String zoomPath) {
            super.onZoomPhotoSuccess(path, zoomPath);
            Log.d("yedona", "onZoomPhotoSuccess: " + path + "\n" + zoomPath);
            Glide.with(MainActivity.this).load(zoomPath).into(iv);
        }
    };
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = ((ImageView) findViewById(R.id.iv));

    }


    public void pickPhoto(View view) {
        YUtils.startForPickGalleryPhoto(this, callBack);
    }

    public void pickPhotoVideo(View view) {
        YUtils.startForPickGalleryPhotoVideo(this, callBack);
    }

    public void pickVideo(View view) {
        YUtils.startForPickGalleryVideo(this, callBack);
    }

    public void pickPhotoEdit(View view) {
        YUtils.startForPickGalleryPhotoAndZoom(this, callBack);
    }

    public void pickPhotoEditConfig(View view) {
        ZoomConfig zoomConfig = new ZoomConfigBuilder().setAspectX(1)
                .setAspectY(1)
                .setOutputX(200)
                .setOutputY(200)
                .setOutputPath("/storage/emulated/0" + "/" + System.currentTimeMillis() + ".jpg").createZoomConfig();
        YUtils.startForPickGalleryPhotoAndZoom(this, zoomConfig, callBack);


    }


    public void takePhotoEdit(View view) {
        ZoomConfig zoomConfig = new ZoomConfigBuilder().setAspectX(1)
                .setAspectY(1)
                .setOutputX(200)
                .setOutputY(200)
                .setOutputPath("/storage/emulated/0" + "/" + System.currentTimeMillis() + ".jpg").createZoomConfig();
        YUtils.startForTakePhotoAndZoom(this, zoomConfig, callBack);
    }

    public void takePhoto(View view) {
        YUtils.startForTakePhoto(this, callBack);
    }

    public void takeVideoConfig(View view) {
        YUtils.startTakeVideo(this,
                new TakeVideoConfigBuilder()
                        .setLength(5)
                        .setQuality(1)
                        .setVideoPath("/storage/emulated/0" + "/" + System.currentTimeMillis() + ".mp4").createTakeVideoConfig(),
                callBack);

    }

    public void takeVideo(View view) {
        YUtils.startTakeVideo(this, callBack);

    }
}
