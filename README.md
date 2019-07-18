# SmartMediaPicker 多媒体选择器 
[![](https://jitpack.io/v/BzCoder/SmartMediaPicker.svg)](https://jitpack.io/#BzCoder/SmartMediaPicker)
[![Build Status](https://www.travis-ci.org/BzCoder/SmartMediaPicker.svg?branch=master)](https://www.travis-ci.org/BzCoder/SmartMediaPicker)


主要还是站在了巨人的肩膀上，封装了以下两个库，修复[仿微信拍照Android控件](https://github.com/CJT2325/CameraView)中存在的几个BUG。也欢迎在提出更多的使用配置需求。欢迎提出issue，一天之内回复。
- [知乎matisse](https://github.com/zhihu/Matisse)
- [仿微信拍照Android控件](https://github.com/CJT2325/CameraView)
## 演示

| 图片选择                  | 仿微信拍照录像                    | 
|:------------------------------:|:---------------------------------:|
|![](image/20190315005039.gif) | ![](image/20190315005454.gif) |

## 改动
### v 1.1.2
  - 只要在设置的时候withMaxImageSelectable（withMaxVideoSelectable）设置为0，就可以只显示视频（图片）
### v 1.1.1
  - 修复内存泄漏问题
  - 修复 withIsMirror 方法命名大小写问题
### v 1.1.0
  - 新增 前置摄像头是否开启镜像翻转选项 isMirror
### v 1.0.9
  - 新增单独调用相机，图片选择器功能
  - SmartMediaPicker.builder()参数改为Fragment与FragmentActivity
  - 修复文字提示 BUG
  - 修复ResultCode BUG
### v 1.0.7
   - 添加实用工具类
   - 不再直接依赖Glide
### v 1.0.6
   - 修复内存泄漏问题
### v 1.0.5
   - 发布
## 使用方法
### gradle添加：
```gradle

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}



	dependencies {
	        implementation 'com.github.BzCoder:SmartMediaPicker:1.1.2'
	}
```
### 代码添加：
```java
        builder = SmartMediaPicker.builder(this)
                //最大图片选择数目 如果不需要图片 将数目设置为0
                .withMaxImageSelectable(5)
                //最大视频选择数目 如果不需要视频 将数目设置为0
                .withMaxVideoSelectable(1)
                //图片选择器是否显示数字
                .withCountable(true)
                //最大视频长度
                .withMaxVideoLength(15 * 1000)
                //最大视频文件大小 单位MB
                .withMaxVideoSize(1)
                //最大图片高度 默认1920
                .withMaxHeight(1920)
                //最大图片宽度 默认1920
                .withMaxWidth(1920)
                //最大图片大小 单位MB
                .withMaxImageSize(5)
                //设置图片加载引擎
                .withImageEngine(new Glide4Engine())
                //前置摄像头拍摄是否镜像翻转图像 默认为true 与微信一致的话为false
                .withIsMirror(false)
	             //弹出类别，默认弹出底部选择栏，也可以选择单独跳转
                .withMediaPickerType(MediaPickerEnum.BOTH)
                .build()
                .show();
```

### ImageEngine 图片加载引擎 :
需要自己实现图片加载，图片加载类需要实现ImageEngine接口，当然也可以直接复制[Glide4Engine.java](https://github.com/BzCoder/SmartMediaPicker/blob/master/app/src/main/java/com/bzcoder/mediapicker/Glide4Engine.java)



### 获取选择的资源：
```java
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<String> resultData = SmartMediaPicker.getResultData(this, requestCode, resultCode, data);
        if (resultData != null && resultData.size() > 0) {
            tv_path.setText(Arrays.toString(resultData.toArray()));
        } else {
            tv_path.setText("NO DATA");
        }
    }
```

### 实用工具类：
- SmartMediaPicker.getFileType(String url) ：获取文件类型
- SmartMediaPicker.getVideoDuration(String path)：获取视频时长
- SmartMediaPicker.getVideoPhoto(String path)：获取视频缩略图
