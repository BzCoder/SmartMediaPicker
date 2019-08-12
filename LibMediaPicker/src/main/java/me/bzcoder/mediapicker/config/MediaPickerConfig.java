package me.bzcoder.mediapicker.config;

import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.ImageEngine;

import java.util.Set;

import me.bzcoder.mediapicker.cameralibrary.JCameraView;
/**
 * 配置类
 * @author : BaoZhou
 * @date : 2019/4/16 9:51
 */
public class MediaPickerConfig {

    /**
     * 选择是否显示数字
     */
    private boolean countable;

    /**
     * 前置摄像头拍摄是否启用镜像
     */
    private boolean isMirror;

    /**
     * 是否可以选择原图
     */
    private boolean originalEnable;

    /**
     * 最大原图大小 单位MB
     */
    private int maxOriginalSize;

    /**
     * 最大图片可选择的数目
     */
    private int maxImageSelectable;

    /**
     * 最大视频可选择的数目
     */
    private int maxVideoSelectable;

    /**
     * 最大图片宽度
     */
    private int maxWidth;

    /**
     * 最大图片高度
     */
    private int maxHeight;

    /**
     * 单位：MB , 默认15MB
     */
    private int maxImageSize;

    /**
     * 单位：毫秒 ,默认20秒
     */
    private int maxVideoLength;

    /**
     * 单位：MB
     */
    private int maxVideoSize;

    /**
     * 图片加载引擎
     */
    private ImageEngine imageEngine;

    /**
     * 弹出类型 有三种类型
     *
     * BOTH：弹出选择栏
     * PHOTO_PICKER：直接跳转图片选择器
     * CAMERA：直接跳转照相机
     */
    private MediaPickerEnum mediaPickerEnum;

    public MediaPickerConfig() {

    }


    public boolean isCountable() {
        return countable;
    }

    public void setCountable(boolean countable) {
        this.countable = countable;
    }


    public boolean isMirror() {
        return isMirror;
    }

    public void setMirror(boolean mirror) {
        isMirror = mirror;
    }

    public boolean isOriginalEnable() {
        return originalEnable;
    }

    public void setOriginalEnable(boolean originalEnable) {
        this.originalEnable = originalEnable;
    }

    public int getMaxImageSelectable() {
        return maxImageSelectable;
    }

    public void setMaxImageSelectable(int maxImageSelectable) {
        this.maxImageSelectable = maxImageSelectable;
    }

    public int getMaxVideoSelectable() {
        return maxVideoSelectable;
    }

    public void setMaxVideoSelectable(int maxVideoSelectable) {
        this.maxVideoSelectable = maxVideoSelectable;
    }

    public int getMaxOriginalSize() {
        return maxOriginalSize;
    }

    public void setMaxOriginalSize(int maxOriginalSize) {
        this.maxOriginalSize = maxOriginalSize;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxImageSize() {
        return maxImageSize;
    }

    public void setMaxImageSize(int maxImageSize) {
        this.maxImageSize = maxImageSize;
    }

    public int getMaxVideoLength() {
        return maxVideoLength;
    }

    public void setMaxVideoLength(int maxVideoLength) {
        this.maxVideoLength = maxVideoLength;
    }

    public int getMaxVideoSize() {
        return maxVideoSize;
    }

    public void setMaxVideoSize(int maxVideoSize) {
        this.maxVideoSize = maxVideoSize;
    }

    public int getCameraMediaType() {
        if (getMaxImageSelectable() == 0 && getMaxVideoSelectable() > 0) {
            return JCameraView.BUTTON_STATE_ONLY_RECORDER;
        } else if (getMaxImageSelectable() > 0 && getMaxVideoSelectable() == 0) {
            return JCameraView.BUTTON_STATE_ONLY_CAPTURE;
        }
        return JCameraView.BUTTON_STATE_BOTH;
    }

    public Set getPhotoPickerMediaType() {
        if (getMaxImageSelectable() == 0 && getMaxVideoSelectable() > 0) {
            return MimeType.ofVideo();
        } else if (getMaxImageSelectable() > 0 && getMaxVideoSelectable() == 0) {
            return MimeType.ofImage();
        }
        return MimeType.ofAll();
    }


    public ImageEngine getImageEngine() {
        return imageEngine;
    }

    public void setImageEngine(ImageEngine imageEngine) {
        this.imageEngine = imageEngine;
    }


    public MediaPickerEnum getMediaPickerEnum() {
        return mediaPickerEnum;
    }

    public void setMediaPickerEnum(MediaPickerEnum mediaPickerEnum) {
        this.mediaPickerEnum = mediaPickerEnum;
    }


}
