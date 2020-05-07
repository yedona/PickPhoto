package com.yedona.takephoto.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;


import com.yedona.takephoto.callback.PermissionCallback;

import java.util.ArrayList;
import java.util.List;


public class PermissionActivity extends Activity {


    private static PermissionCallback mPermissionCallback;

    public static void start(Context context, String[] permissions, PermissionCallback callback) {

        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra("permissions", permissions);
        mPermissionCallback = callback;
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 当api大于23时，才进行权限申请
        String[] permissions = getIntent().getStringArrayExtra("permissions");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null && permissions.length > 0) {
            requestPermissions(permissions, 0x01);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 处理申请结果
        boolean[] shouldShowRequestPermissionRationale = new boolean[permissions.length];
        for (int i = 0; i < permissions.length; ++i) {
            shouldShowRequestPermissionRationale[i] = shouldShowRequestPermissionRationale(permissions[i]);
        }
        this.onRequestPermissionsResult(permissions, grantResults, shouldShowRequestPermissionRationale);
    }

    @TargetApi(23)
    void onRequestPermissionsResult(String[] permissions, int[] grantResults, boolean[] shouldShowRequestPermissionRationale) {
        int length = permissions.length;
        int granted = 0;

        List<String> mPermissionRejectList = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                if (!shouldShowRequestPermissionRationale[i]) {
                    mPermissionRejectList.add(permissions[i]);
                }
            } else {
                granted++;
            }
        }
        if (granted == length) {
            mPermissionCallback.onPermissionSuccess();
        } else {
            mPermissionCallback.onPermissionFailure(mPermissionRejectList.toArray(new String[mPermissionRejectList.size()]));
        }
        mPermissionCallback = null;
        finish();
    }


}
