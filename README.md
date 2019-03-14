# MediaPicker 多媒体选择器 
[![](https://jitpack.io/v/BzCoder/MediaPicker.svg)](https://jitpack.io/#BzCoder/MediaPicker)

一款方便好用的多媒体选择器。封装了以下两个库。
- [知乎matisse](https://github.com/CJT2325/CameraView)
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
	        implementation 'com.github.BzCoder:MediaPicker:1.0.0'
	}
```
代码添加：
```java
CameraDialogUtil.builder(getSupportFragmentManager())
                        .withMaxImageSelectable(5)
                        .withMaxVideoSelectable(1)
                        .withCountable(true)
                        .withMaxVideoLength(15 * 1000)
                        .withMaxVideoSize(1)
                        .withMaxHeight(100)
                        .withMaxImageSize(5)
                        .withMaxWidth(50)
                        .build()
                        .show();

