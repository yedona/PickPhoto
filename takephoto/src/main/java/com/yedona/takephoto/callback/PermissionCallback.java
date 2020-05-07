package com.yedona.takephoto.callback;

public interface PermissionCallback {


    /**
     * 权限申请成功
     */
    void onPermissionSuccess();

    /**
     * 获取权限失败
     */
    void onPermissionFailure(String[] permission);

}
