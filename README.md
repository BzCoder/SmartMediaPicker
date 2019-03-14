# MediaPicker
一款方便好用的多媒体选择器。
封装了以下两个库。
- [知乎matisse](https://github.com/CJT2325/CameraView)
- [仿微信拍照Android控件](https://github.com/CJT2325/CameraView)
## 使用方法
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

```
