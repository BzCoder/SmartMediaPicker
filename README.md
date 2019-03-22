# SmartMediaPicker 多媒体选择器 
[![](https://jitpack.io/v/BzCoder/SmartMediaPicker.svg)](https://jitpack.io/#BzCoder/SmartMediaPicker)

主要还是站在了巨人的肩膀上，封装了以下两个库，修复[仿微信拍照Android控件](https://github.com/CJT2325/CameraView)中存在的几个BUG。也欢迎在提出更多的使用配置需求。
- [知乎matisse](https://github.com/zhihu/Matisse)
- [仿微信拍照Android控件](https://github.com/CJT2325/CameraView)
## 演示

| 图片选择                  | 仿微信拍照录像                    | 
|:------------------------------:|:---------------------------------:|
|![](image/20190315005039.gif) | ![](image/20190315005454.gif) |


## 使用方法
gradle添加：
```gradle

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}



	dependencies {
	        implementation 'com.github.BzCoder:SmartMediaPicker:1.0.6'
	}
```
代码添加：
```java
  SmartMediaPicker.builder(getSupportFragmentManager())
                        //最大图片选择数目
                        .withMaxImageSelectable(5)
                        //最大视频选择数目
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
                        .build()
                        .show();
```

获取选择的资源：

```java
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<String> resultData = SmartMediaPicker.getResultData(this, requestCode, resultCode, data);
```
