# PickPhoto
调用系统相册选择图片视频，调用系统相机拍照录像

1、依赖

 1). 在根部目录的 build.gradle添加maven仓库



       allprojects {

		repositories {
                
			...
			maven { url 'https://jitpack.io' }
		}
                
	   }

 2). 在模块下方添加

       dependencies {
  
            implementation 'com.github.yedona:PickPhoto:1.0.0'
   
       }
  
2.使用方式

 1). 选取系统相册图片

    YUtils.startForPickGalleryPhotoVideo（Activity , BaseCallBack）
 
 2). 调用系统相册图片并裁剪

    ZoomConfig zoomConfig = new ZoomConfigBuilder().setAspectX(1)
                .setAspectY(1)
                .setOutputX(200)
                .setOutputY(200)
                .setOutputPath("/storage/emulated/0" + "/" + System.currentTimeMillis() + ".jpg").createZoomConfig();
		
    YUtils.startForPickGalleryPhotoAndZoom(Activity, zoomConfig, BaseCallBack);
 
 3).调用系统相机拍照

    YUtils.startForTakePhoto(Activity, BaseCallBack);

 4).调用系统相机录像

     YUtils.startTakeVideo(Activity,
                new TakeVideoConfigBuilder()
                        .setLength(5)
                        .setQuality(1)
                        .setVideoPath("/storage/emulated/0" + "/" + System.currentTimeMillis() + ".mp4")
			 .createTakeVideoConfig(),
                BaseCallBack);
